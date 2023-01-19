package com.olegpruh.internetshop.util;

public class StringUtil {
    public static String capitalize(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
