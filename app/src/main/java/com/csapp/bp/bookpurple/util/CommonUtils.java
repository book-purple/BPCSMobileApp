package com.csapp.bp.bookpurple.util;

/*
 * Written by gauravsharma on 2019-05-19.
 */
public class CommonUtils {

    public static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
