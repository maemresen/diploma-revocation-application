package com.maemresen.fxregisterapp.utility;

import com.maemresen.fxregisterapp.constants.FieldName;
import com.maemresen.fxregisterapp.constants.PrimaryKey;
import com.maemresen.fxregisterapp.constants.RequiredField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Date;

public class FieldHelper {

    public static Field[] removePrimaryKey(Field[] fields) {

        Field primary = getPrimary(fields);

        int fLen = fields.length;
        int len = (primary == null) ? (fLen) : (fLen - 1);

        Field[] result = new Field[len];

        int nIndex = 0;
        for (Field field : fields) {
            if (field.equals(primary)) {
                continue;
            }
            result[nIndex] = field;
            nIndex++;
        }
        return result;
    }

    public static Field[] removeEmptyFields(Object object, Field[] fields) throws IllegalAccessException {

        int count = fields.length;
        for (Field field : fields) {
            if (isFieldEmpty(object, field)) {
                count--;
            }
        }

        Field[] result = new Field[count];
        int index = 0;
        for (Field field : fields) {
            if (isFieldEmpty(object, field)) {
                continue;
            }
            result[index] = field;
            index++;
        }

        return result;
    }

    private static boolean isFieldEmpty(Object object, Field field) throws IllegalAccessException {

        field.setAccessible(true);
        Class<?> type = field.getType();

        Object value = field.get(object);

        if (value == null) {
            return true;
        }

        if (type == String.class) {
            return ((String) value).equalsIgnoreCase("");
        }

        field.setAccessible(false);
        return false;
    }

    public static Field getPrimary(Field[] fields) {
        int len = fields.length;
        for (int i = 0; i < len; i++) {
            Field field = fields[i];
            if (isPrimaryKey(field)) {
                return field;
            }
            i++;
        }
        return null;
    }

    public static Object getObjectValue(Class<?> type, Object value) {

        if (value == null) {
            return null;
        }

        if ((type == String.class || type == Date.class) && ((String) value).equalsIgnoreCase("")) {
            return null;
        }

        return value;
    }


    public static boolean validateDate(Object date) {

        if (date == null) {
            return true;
        }

        Class<?> clazz = date.getClass();


        if (clazz == String.class) {
            return ((String) date).equalsIgnoreCase("") || ((String) date).matches(getRegex());

        }


        if (clazz == Date.class) {
            String str = date.toString();
            return str.equalsIgnoreCase("") || str.matches(getRegex());
        }
        return false;
    }


    private static String getRegex() {
        return "(0[1-9]|[12]\\d|3[01])\\/(0[1-9]|1[0-2])\\/(\\d{4})";
    }


    /**/

    public static boolean isPrimaryKey(Field field) {
        field.setAccessible(true);

        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == PrimaryKey.class) {
                return true;
            }
        }
        field.setAccessible(false);
        return false;
    }

    public static String isFieldRequired(Field field) {
        field.setAccessible(true);
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == RequiredField.class) {
                return ((RequiredField) annotation).message();
            }
        }
        field.setAccessible(false);
        return null;
    }

    public static boolean isRequired(Field field) {
        return isFieldRequired(field) != null;
    }

    public static String getFieldName(Field field) {
        field.setAccessible(true);
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == FieldName.class) {
                return ((FieldName) annotation).name();
            }
        }
        field.setAccessible(false);
        return "-";
    }

}