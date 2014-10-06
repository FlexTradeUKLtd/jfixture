package com.flextrade.jfixture.utility;

public final class StringUtil {

    public static String repeat(String s, int n) {
        if (s == null || n < 1) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
