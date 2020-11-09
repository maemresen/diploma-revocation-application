package com.maemresen.fxregisterapp.view.controllers.layouts;


import com.maemresen.fxregisterapp.utility.DialogHelper;
import com.maemresen.fxregisterapp.utility.FieldHelper;
import com.maemresen.fxregisterapp.database.jdbc.mapper.StudentController;
import com.maemresen.fxregisterapp.model.Student;

import com.maemresen.fxregisterapp.view.StudentForTable;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class RegisterFormController {

    private StudentForTable selectedStudent;

    @FXML
    private VBox inputBox;

    // Label contains info about selected student
    @FXML
    private Label selectedStudentInfo;

    // button to register a student
    @FXML
    public Button registerStudentButton;

    // button to clear selection
    @FXML
    public Button clearSelectionButton;

    // button to make search
    @FXML
    public Button searchButton;

    @SuppressWarnings("unused")
    public void initialize() {

        clearSelectedStudent();

        Field[] fields = Student.class.getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            if (FieldHelper.isPrimaryKey(field)) {
                continue;
            }


            inputBox.getChildren().add(getRow(field));

            field.setAccessible(false);
        }

    }

    private HBox getRow(Field field) {

        String id = field.getName() + "Input";
        String name = FieldHelper.getFieldName(field) +
                (FieldHelper.isRequired(field) ? "*" : "");

        Label label = new Label(name);
        label.setPrefWidth(200);

        TextField textField = new TextField();
        textField.setId(id);

        if (field.getName().equalsIgnoreCase("ssn")) {
            onlyNumeric(textField);
        }

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(label, textField);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        return hBox;
    }

    /* Response of the operation */


    // to get student from fields
    private Student getStudentFromFields() {
        return getStudentFromFields(true);
    }

    private Student getStudentFromFields(boolean required) {

        try {
            Student student = new Student();
            Field[] fields = student.getClass().getDeclaredFields();

            for (Field field : fields) {

                if (FieldHelper.isPrimaryKey(field)) {
                    continue;
                }

                Object value = FieldHelper.getObjectValue(field.getType(), getInputByField(field));
                String errorMessage = FieldHelper.isFieldRequired(field);

                if (required && (value == null && errorMessage != null)) {
                    setResponse(errorMessage);
                    return null;
                }

                Class<?> fieldType = field.getType();


                if (fieldType == Date.class) {

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                    if (!FieldHelper.validateDate(value)) {
                        setResponse("Tarihleri GG/AA/YYYY Formatında Giriniz");
                        return null;
                    }

                    if (value != null) {
                        java.util.Date utilDate;

                        utilDate = formatter.parse((String) value);
                        value = new Date(utilDate.getTime());

                    }


                }

                field.setAccessible(true);
                field.set(student, value);
                field.setAccessible(false);
            }
            student.setStudentID(selectedStudent == null ? -1 : selectedStudent.studentIDProperty().getValue());

            return student;
        } catch (ParseException | IllegalAccessException e) {
            setErr(e);
            return null;
        }

    }

    /**/
    private Object getInputByField(Field field) {
        Node node = inputBox.getScene().lookup("#" + field.getName() + "Input");

        if (node == null) {
            return null;
        }
        Class<?> type = node.getClass();

        if (type == TextField.class) {
            return ((TextField) node).getText();
        }

        return null;
    }


    private void setFieldToInput(Field field, Object object) {
        try {
            field.setAccessible(true);

            Node node = inputBox.getScene().lookup("#" + field.getName() + "Input");
            if (node == null) {
                return;
            }

            Object value = field.get(object);

            Class<?> type = field.getType();


            if (type == String.class) {
                ((TextField) node).setText((String) value);
                return;
            }

            if (type == int.class || type == Integer.class) {
                ((TextField) node).setText(String.valueOf(value));
                return;
            }

            if (type == Date.class) {
                Date date = (Date) value;
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");// FOrmat in This Format or you change Change as well
                ((TextField) node).setText(value == null ? "" : format.format(date));
            }

            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            setErr(e);
        }

    }

    // to clean all input fields
    private void clearAllInputs() {

        Student student = new Student();
        Field[] fields = student.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (FieldHelper.isPrimaryKey(field)) {
                continue;
            }

            clearInput(field);
        }
    }


    private void clearInput(Field field) {


        Scene scene = inputBox.getScene();
        if (scene == null) {
            return;
        }
        Node node = scene.lookup("#" + field.getName() + "Input");

        if (node == null) {
            return;
        }

        Class<?> type = node.getClass();
        if (type == TextField.class) {
            ((TextField) node).clear();
            return;
        }
        if (type == DatePicker.class) {
            ((DatePicker) node).setValue(null);
        }

    }

    @FXML
    public void clearSelectedStudent() {
        setSelectedStudent(null);
        registerStudentButton.setText("Ekle");
    }

    // to fill input fields with selected student from the table
    public void setSelectedStudent(StudentForTable studentForTable) {

        try {
            selectedStudent = studentForTable;

            if (selectedStudent == null) {
                selectedStudentInfo.setText("-");
                clearAllInputs();
                return;
            }

            selectedStudentInfo.setText(selectedStudent.studentNameProperty().getValue());
            fillFields();
        } catch (IllegalAccessException e) {
            setErr(e);
        }

    }

    private void fillFields() throws IllegalAccessException {
        fillFields(selectedStudent);
    }

    private void fillFields(StudentForTable studentForTable) {
        Student student = studentForTable.getStudent();

        if (student == null) {
            clearAllInputs();
            return;
        }

        Field[] fields = student.getClass().getDeclaredFields();
        for (Field field : fields) {

            if (FieldHelper.isPrimaryKey(field)) {
                continue;
            }

            setFieldToInput(field, student);

        }
    }

    /*---------------------------------------------
        to try save student info to db
     ---------------------------------------------*/
    public void tryRegisterSelectedStudentInfo() {

        Student student = null;
        try {
            student = getStudentFromFields();

            if (student == null) {
                //TODO: clearAllInputs()...
                return;
            }

            if (StudentController.getInstance().hasStudentWithId(student.getStudentID())) {
                tryUpdateStudent(student);
                return;
            }

            tryInsertStudent(student);
        } catch (SQLException e) {
            setErr(e);
        }

    }

    // to insert student
    private void tryInsertStudent(Student student) {
        if (DialogHelper.confirmationDialogForInsert()) {
            insertStudent(student);
            clearSelectedStudent();
        }
    }

    private void insertStudent(Student student) {
        try {
            StudentController.getInstance().insertStudent(student);
        } catch (SQLException e) {
            setErr(e);
        }
    }

    // to update student
    private void tryUpdateStudent(Student student) {
        if (DialogHelper.confirmationDialogForUpdate()) {
            updateStudent(student);
            clearSelectedStudent();
        }
    }

    private void updateStudent(Student student) {
        try {
            StudentController.getInstance().updateStudent(student);
        } catch (SQLException e) {
            setErr(e);
        }
    }

    // to search student
    public List<Student> searchStudent() {
        try {
            Student student = getStudentFromFields(false);
            if (student == null) {
                return null;
            }

            return StudentController.getInstance().searchStudent(student);
        } catch (SQLException e) {
            setErr(e);
            return null;
        }

    }

    private void setErr(Exception e) {
        e.printStackTrace();
        DialogHelper.setResponse(e.getMessage());
    }

    private void setResponse(String msg) {
        DialogHelper.setResponse(msg);
    }
    
    // SSN control
    private void onlyNumeric(TextField textField) {
        int maxLength = 11;

        // force the field to be numeric only
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            if (newValue.length() > maxLength) {
                textField.setText(newValue.substring(0, maxLength));
            }

        });
    }

}
