package com.flextrade.jfixture.utility;

public class Clock implements TimeProvider {
    @Override
    public long getCurrentTimeInMilliseconds() {
        return System.currentTimeMillis();
    }
}
