package com.maemresen.fxregisterapp.database.jdbc.mapper;


import com.maemresen.fxregisterapp.database.StudentDAO;

import com.maemresen.fxregisterapp.database.jdbc.DatabaseHandler;
import com.maemresen.fxregisterapp.model.Student;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A Static Class To Manipulate DatabaseHandler Operations...
 */
public class StudentController implements StudentDAO {


    private static volatile StudentController instance = null;

    public static StudentController getInstance() throws SQLException {
        if (instance == null) {
            synchronized (StudentController.class) {
                if (instance == null) {
                    instance = new StudentController();
                }
            }
        }
        return instance;
    }


    /**/
    private DatabaseHandler databaseHandler;

    private StudentController() throws SQLException {
        this.databaseHandler = new DatabaseHandler();
    }

    /* CRUD */
    @Override
    public void insertStudent(Student student) {
        databaseHandler.insertObject(student);

    }

    @Override
    public void updateStudent(Student student) {
        databaseHandler.updateObject(student);
    }


    @Override
    public List<Student> getStudentList() {

        List<Student> list = new ArrayList<>();
        ResultSet rs;
        try {
            rs = databaseHandler.select("SELECT * FROM Student");

            if (rs == null) {
                return new ArrayList<>();
            }

            while (rs.next()) {
                Student student = getStudentFromResultSet(rs);
                list.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public Student getStudentByStudentID(int studentID) {
        String query = "SELECT * FROM Student WHERE studentID= ?";
        try {
            PreparedStatement preparedStatement = databaseHandler.getPreparedStatement(query);
            preparedStatement.setInt(1, studentID);
            ResultSet rs = databaseHandler.select(preparedStatement);
            Student student = null;
            while (rs.next()) {
                student = getStudentFromResultSet(rs);
            }

            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Student> searchStudent(Student student) {

        List<Student> list = new ArrayList<>();
        ResultSet rs;
        try {
            rs = databaseHandler.searchObject(student);

            if (rs == null) {
                return new ArrayList<>();
            }

            while (rs.next()) {
                Student s = getStudentFromResultSet(rs);
                list.add(s);
            }

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
    }

    private Student getStudentFromResultSet(ResultSet rs) {

        try {

            Student student = new Student();

            Field[] fields = Student.class.getDeclaredFields();

            for (Field field : fields) {

                field.setAccessible(true);

                Class<?> type = field.getType();
                String fieldName = field.getName();

                if (type == String.class) {
                    field.set(student, rs.getString(fieldName));
                }

                if (type == int.class || type == Integer.class) {
                    field.set(student, rs.getInt(fieldName));
                }

                if (type == Date.class) {
                    field.set(student, rs.getDate(fieldName));
                }

                field.setAccessible(false);
            }

            return student;

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public Boolean hasStudentWithId(int studentID) {
        return getStudentByStudentID(studentID) != null;
    }


}
