package com.flextrade.jfixture.mockito.testtypes;

public interface OrderInterface {
    String getSymbol();
    void setSymbol(String symbol);

    int getSize();
    void setSize(int size);

    double getPrice();
    void setPrice(double price);

    void writeToDatabase();
}
