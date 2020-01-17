package com.flextrade.jfixture.utility.comparators;

import com.flextrade.jfixture.utility.IntegerPolyfill;

import java.lang.reflect.Constructor;
import java.util.Comparator;

// Duplicate code: ideally this would be compare(Executable, Executable),
// but that base class was introduced in Java 8, and the current build is Java 6.
@SuppressWarnings("Duplicates")
public class ConstructorParameterCountComparator implements Comparator<Constructor<?>> {

    @Override
    public int compare(Constructor<?> ctor1, Constructor<?> ctor2) {
        int ctor1ParameterCount = ctor1.getGenericParameterTypes().length;
        int ctor2ParameterCount = ctor2.getGenericParameterTypes().length;

        int result = IntegerPolyfill.compare(ctor1ParameterCount, ctor2ParameterCount);
        if (result == 0) {
            // It is necessary to compare the signature as a disambiguation after the param count comparison,
            // because Class.getConstructors() has no guarantees on order
            // and using this comparator in a stable sort could yield different results in different runs.
            return ctor1.toString().compareTo(ctor2.toString());
        }
        return result;
    }
}
