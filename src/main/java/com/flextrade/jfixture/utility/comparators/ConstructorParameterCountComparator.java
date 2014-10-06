package com.flextrade.jfixture.utility.comparators;

import java.lang.reflect.Constructor;
import java.util.Comparator;

public class ConstructorParameterCountComparator implements Comparator<Constructor<?>> {

    @Override
    public int compare(Constructor ctor1, Constructor ctor2) {
        Integer ctor1Parameters = ctor1.getGenericParameterTypes().length;
        Integer ctor2Parameters = ctor2.getGenericParameterTypes().length;

        return ctor1Parameters.compareTo(ctor2Parameters);
    }
}
