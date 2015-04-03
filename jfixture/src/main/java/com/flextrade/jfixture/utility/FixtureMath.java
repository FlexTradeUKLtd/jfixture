package com.flextrade.jfixture.utility;

import java.util.Random;

public class FixtureMath {

    private static final Random RND = new Random();

    public static long randLong(long min, long max) {
        long randomLong = nextLong((max - min) + 1) + min;

        return randomLong;
    }

    // http://stackoverflow.com/a/2546186/255231
    public static long nextLong(long n) {
        // error checking and 2^x checking removed for simplicity.
        long bits, val;
        do {
            bits = (RND.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits-val+(n-1) < 0L);
        return val;
    }
}
