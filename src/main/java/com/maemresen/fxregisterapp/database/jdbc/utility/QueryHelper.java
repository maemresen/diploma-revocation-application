package com.maemresen.fxregisterapp.database.jdbc.utility;

import com.maemresen.fxregisterapp.utility.FieldHelper;

import java.lang.reflect.Field;
import java.sql.Date;

public class QueryHelper {

    public static String getInsertQuery(Object object) {

        Class clazz = object.getClass();
        Field[] fields = FieldHelper.removePrimaryKey(clazz.getDeclaredFields());
        StringBuilder query = new StringBuilder("INSERT INTO " + clazz.getSimpleName() + " (");
        int len = fields.length;

        for (int i = 0; i < len; i++) {
            Field field = fields[i];
            boolean isLast = (i == (len - 1));
            String predicate = getCharArrayElement(getPredicate(field), isLast);
            query.append(predicate);
        }


        query.append(") VALUES (");

        for (int i = 0; i < len; i++) {
            Field field = fields[i];

            if (FieldHelper.isPrimaryKey(field)) {
                continue;
            }


            boolean isLast = (i == (len - 1));
            query.append(getCharArrayElement("?", isLast));
        }


        return query.append(")").toString();
    }

    private static String getCharArrayElement(String element, boolean isLast) {
        return element + (isLast ? "" : ", ");
    }

    private static String getPredicate(Field field) {

        field.setAccessible(true);
        String result = field.getName();
        field.setAccessible(false);

        return result;
    }


    /**/
    public static String getUpdateQuery(Object object) {

        Class clazz = object.getClass();
        Field[] fields = FieldHelper.removePrimaryKey(clazz.getDeclaredFields());
        Field primary = FieldHelper.getPrimary(clazz.getDeclaredFields());
        StringBuilder query = new StringBuilder("UPDATE Student " + clazz.getSimpleName() + " SET ");
        int len = fields.length;

        for (int i = 0; i < len; i++) {
            Field field = fields[i];
            boolean isLast = (i == (len - 1));
            query.append(field.getName()).append("=?").append(isLast ? "" : ", ");
        }

        assert primary != null;
        return query.append(" WHERE ").append(primary.getName()).append("=?").toString();
    }

    public static String getSearchQuery(Object object) throws IllegalAccessException {

        Class clazz = object.getClass();
        Field[] fields = FieldHelper.removePrimaryKey(clazz.getDeclaredFields());
        fields = FieldHelper.removeEmptyFields(object, fields);

        int len = fields.length;

        StringBuilder query = new StringBuilder("SELECT * FROM " + clazz.getSimpleName());

        if (len != 0) {
            query.append(" WHERE ");
        }

        for (int i = 0; i < len; i++) {
            Field field = fields[i];
            boolean isLast = (i == (len - 1));
            query.append(field.getName()).append(" LIKE ");
            query.append(getFieldValue(object, field));

            query.append(isLast ? "" : " AND ");
        }
        return query.toString();
    }

    private static String getFieldValue(Object object, Field field) throws IllegalAccessException {

        field.setAccessible(true);
        Class<?> type = field.getType();

        Object value = field.get(object);

        if (value == null) {
            return null;
        }

        String result = "%" + value.toString() + "%";

        if (type == String.class || type == Date.class) {
            result = "'" + result + "'";
        }

        field.setAccessible(false);
        return result;

    }
}


