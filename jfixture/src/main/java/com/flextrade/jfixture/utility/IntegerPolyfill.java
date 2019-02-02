package com.flextrade.jfixture.utility;

public class IntegerPolyfill {
    /**
     * @see Integer#compare(int, int) Integer.compare since Java 7
     */
    public static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }
}
