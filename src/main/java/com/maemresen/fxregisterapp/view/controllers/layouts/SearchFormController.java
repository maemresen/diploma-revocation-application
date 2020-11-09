package com.maemresen.fxregisterapp.view.controllers.layouts;

import com.maemresen.fxregisterapp.database.jdbc.mapper.StudentController;
import com.maemresen.fxregisterapp.model.Student;

import com.maemresen.fxregisterapp.utility.DialogHelper;
import com.maemresen.fxregisterapp.view.StudentForTable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchFormController {

    private static final List<TableColumn<StudentForTable, ? extends ObservableValue>> tableColumnList = new ArrayList<>();
    private static final String[] labels = new String[]{"TC No", "Ad", "Soyad", "Baba Adı", "Ana Adı", "Doğum Yeri",
            "Doğum Tarihi", "Üniversite", "Fakülte", "Bölüm", "Diploma Seri No", "İl", "İlçe", "Tarih", "Dosya No"};

    private static final Callback[] callbacks = new Callback[]{
            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().ssnProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentNameProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentSurnameProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentFatherNameProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentMotherNameProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentBirthDateProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentBirthPlaceProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentUniversityProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentFacultyProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentDepartmentProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentDiplomaSerialNoProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentProvinceProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentCountryProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().studentRegisterDateProperty(),

            (Callback<TableColumn.CellDataFeatures<StudentForTable, String>, ObservableValue<String>>)
                    param -> param.getValue().registerNoProperty(),
    };

    /**/
    public TableView<StudentForTable> resultTable;

    /**/

    @SuppressWarnings("WeakerAccess")
    public Label resultCount;

    /**/
    public void initialize() {

        resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        initTable();

    }


    //to initialize table configurations
    private void initTable() {

        int len = callbacks.length;
        for (int i = 0; i < len; i++) {

            Callback callback = callbacks[i];
            @SuppressWarnings("unchecked") TableColumn<StudentForTable, ? extends ObservableValue> column = getTableColumn(callback);
            String label = labels[i];
            column.setMinWidth(150);
            column.setPrefWidth(Control.USE_COMPUTED_SIZE);
            column.setText(label);
            tableColumnList.add(column);

        }
        resultTable.getColumns().addAll(tableColumnList);
    }

    /**/
    private static <T> TableColumn getTableColumn(Callback<TableColumn.CellDataFeatures<StudentForTable, T>, ObservableValue<T>> callback) {
        TableColumn<StudentForTable, T> column1 = new TableColumn<>();
        column1.setCellValueFactory(callback);
        return column1;
    }


    /*
        Events
    */
    public void listStudents() {
        try {
            listStudents(StudentController.getInstance().getStudentList());
        } catch (SQLException e) {
            e.printStackTrace();
            DialogHelper.setResponse(e.getMessage());
        }
    }

    public void listStudents(List<Student> studentList) {
        if (studentList == null) {
            return;
        }
        resultTable.getItems().clear();
        List<StudentForTable> studentForTableList = getStudentList(studentList);
        resultCount.setText("Bulunan Öğrenci Sayısı: " + String.valueOf(studentForTableList.size()));

        ObservableList<StudentForTable> data = FXCollections.observableArrayList(studentForTableList);
        resultTable.setItems(data);
    }

    private List<StudentForTable> getStudentList(List<Student> studentList) {

        List<StudentForTable> studentForTableList = new ArrayList<>();
        for (Student student : studentList) {
            if (student == null) {
                continue;
            }
            StudentForTable sft = new StudentForTable(student);
            studentForTableList.add(sft);
        }
        return studentForTableList;
    }


}
