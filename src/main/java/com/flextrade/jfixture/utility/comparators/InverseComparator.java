package com.flextrade.jfixture.utility.comparators;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;

@RequiredArgsConstructor
public class InverseComparator<T> implements Comparator<T> {

    private final Comparator<T> original;

    @Override
    public int compare(T o1, T o2) {
        return this.original.compare(o2, o1);
    }
}
