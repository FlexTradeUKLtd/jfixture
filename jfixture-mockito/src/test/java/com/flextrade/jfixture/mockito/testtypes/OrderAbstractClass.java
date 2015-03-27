package com.flextrade.jfixture.mockito.testtypes;

public abstract class OrderAbstractClass {
    public abstract String getSymbol();

    public abstract void setSymbol(String symbol);

    public abstract int getSize();

    public abstract void setSize(int size);

    public abstract double getPrice();

    public abstract void setPrice(double price);
}
