package com.maemresen.fxregisterapp.database.jdbc;

import com.maemresen.fxregisterapp.database.jdbc.utility.QueryHelper;
import com.maemresen.fxregisterapp.utility.FieldHelper;

import java.lang.reflect.Field;
import java.sql.*;

public class DatabaseHandler {

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**/
    private Connection dbConnection;

    public DatabaseHandler() throws SQLException {
        connectDB();
    }


    private void connectDB() throws SQLException {
        initDataBaseConnection();
        initTables();
    }

    private void initDataBaseConnection() throws SQLException {
        if (dbConnection == null) {
            dbConnection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        }
    }

    private void initTables() throws SQLException {


        Statement stmt = dbConnection.createStatement();

        String sql = "CREATE TABLE Student("
                + "studentID INT NOT NULL AUTO_INCREMENT,"
                + "ssn VARCHAR(20) NULL,"
                + "studentName VARCHAR(50) NOT NULL,"
                + "studentSurname VARCHAR(50) NOT NULL,"
                + "studentFatherName VARCHAR(50) NULL,"
                + "studentMotherName VARCHAR(50) NULL,"
                + "studentBirthPlace VARCHAR(50) NULL,"
                + "studentBirthDate DATE NULL,"
                + "studentUniversity VARCHAR(50) NULL,"
                + "studentFaculty VARCHAR(50) NULL,"
                + "studentDepartment VARCHAR(50) NULL,"
                + "studentDiplomaSerialNo VARCHAR(50) NULL,"
                + "studentProvince VARCHAR(50) NULL,"
                + "studentCountry VARCHAR(50) NULL,"
                + "studentRegisterDate DATE NULL,"
                + "registerNo VARCHAR(50) NULL,"
                + "registerDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                + "PRIMARY KEY(studentID)"
                + ")";
        stmt.execute(sql);
    }

    /**
     * To Validate DatabaseHandler Connection...
     *
     * @throws SQLException ..
     */
    private void validateConnection() throws SQLException {
        if (isConnectionClosed()) {
            connectDB();
        }
    }

    private boolean isConnectionClosed() throws SQLException {
        initDataBaseConnection();
        return dbConnection.isClosed();
    }

    /* CRUD */
    public ResultSet select(String query) throws SQLException {
        validateConnection();
        Statement stmt = dbConnection.createStatement();
        return stmt.executeQuery(query);
    }

    public ResultSet select(PreparedStatement preparedStatement) throws SQLException {
        validateConnection();
        return preparedStatement.executeQuery();
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        validateConnection();
        return dbConnection.prepareStatement(query);
    }

    /* misc */
    public void insertObject(Object object) {

        try {

            Class clazz = object.getClass();
            Field[] fields = FieldHelper.removePrimaryKey(clazz.getDeclaredFields());
            int len = fields.length;

            PreparedStatement preparedStatement = getPreparedStatement(QueryHelper.getInsertQuery(object));

            for (int i = 0; i < len; i++) {
                setPreparedStatement(object, fields[i], i, preparedStatement);
            }

            insert(preparedStatement);
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void updateObject(Object object) {

        try {

            Class clazz = object.getClass();
            Field[] fields = FieldHelper.removePrimaryKey(clazz.getDeclaredFields());
            Field primary = FieldHelper.getPrimary(clazz.getDeclaredFields());

            int len = fields.length;

            PreparedStatement preparedStatement = getPreparedStatement(QueryHelper.getUpdateQuery(object));

            for (int i = 0; i < len; i++) {
                setPreparedStatement(object, fields[i], i, preparedStatement);
            }

            setPreparedStatement(object, primary, len, preparedStatement);
            update(preparedStatement);

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public ResultSet searchObject(Object object) throws IllegalAccessException, SQLException {
        return select(QueryHelper.getSearchQuery(object));
    }

    private void setPreparedStatement(Object object, Field field, int i, PreparedStatement preparedStatement) throws IllegalAccessException, SQLException {

        field.setAccessible(true);
        Class<?> type = field.getType();

        Object value = field.get(object);

        if (type == String.class) {
            preparedStatement.setString(i + 1, (String) value);
        } else if (type == Integer.class || type == int.class) {
            preparedStatement.setInt(i + 1, (Integer) value);
        } else if (type == Date.class) {
            preparedStatement.setDate(i + 1, (Date) value);
        }

        field.setAccessible(false);
    }

    /* Execute */
    private void insert(PreparedStatement preparedStatement) {
        execute(preparedStatement);
    }

    private void update(PreparedStatement preparedStatement) {
        execute(preparedStatement);
    }

    private void execute(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
