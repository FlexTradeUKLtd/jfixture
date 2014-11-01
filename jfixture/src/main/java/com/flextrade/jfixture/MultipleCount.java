package com.flextrade.jfixture;

public class MultipleCount {

    private int count = 3;

    public MultipleCount() { }

    public MultipleCount(int initialCount) {
        this.count = initialCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count must not be less than zero");
        }

        this.count = count;
    }
}
