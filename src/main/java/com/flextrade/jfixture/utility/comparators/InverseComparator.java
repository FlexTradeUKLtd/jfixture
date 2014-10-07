package com.flextrade.jfixture.utility.comparators;

import java.util.Comparator;

public class InverseComparator<T> implements Comparator<T> {

    private final Comparator<T> original;

    public InverseComparator(Comparator<T> original) {
        this.original = original;
    }

    @Override
    public int compare(T o1, T o2) {
        return this.original.compare(o2, o1);
    }
}
