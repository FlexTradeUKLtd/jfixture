package com.flextrade.jfixture.jupiter;

public class Data<T> {
    private final T value;

    public Data(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
