package com.flextrade.jfixture.utility;

import java.util.ArrayList;
import java.util.List;

/*
 * Very basic implementation of a circular list to be used internally
 */
public class CircularList<T> {

    private List<T> source;
    private int currentIndex = 0;

    public CircularList(List<T> source) {
        if(source == null)
            throw new IllegalArgumentException("source is null");
        if(source.size() == 0)
            throw new IllegalArgumentException("source has zero elements");

        this.source = new ArrayList<T>(source);
    }

    public T next() {
        if(currentIndex == source.size())
            currentIndex = 0;

        T value = source.get(currentIndex);
        currentIndex++;

        return value;
    }
}
