package com.maemresen.fxregisterapp.utility;

public class StringHelper {

    public static String validateDate(String date) {
        if (date.matches("(0[1-9]|[12]\\d|3[01])\\.([1-9]|1[0-2])\\.(\\d{4})")) {
            return date;
        }
        return null;
    }


}
