package com.flextrade.jfixture.mockito.testtypes;

public class PropertyUtilTestClass {

    private int size;
    private String symbol;
    private int id;

    public int getId(int anotherId) {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int theSize() {
        return size;
    }
}
