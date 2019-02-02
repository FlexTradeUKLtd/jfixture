package com.flextrade.jfixture.utility.comparators;

import com.flextrade.jfixture.utility.IntegerPolyfill;

import java.lang.reflect.Constructor;
import java.util.Comparator;

/**
 * Compare by method parameter count, but if they're equal compare by the actual parameter types.
 * This is necessary as {@link Class#getConstructors()} has no guarantees on order,
 * and for reproducible tests the objects must be constructed in the same way every time a test is run.
 */
public class ConstructorParameterCountComparator implements Comparator<Constructor<?>> {

    @Override
    public int compare(Constructor<?> ctor1, Constructor<?> ctor2) {
        int ctor1ParameterCount = ctor1.getGenericParameterTypes().length;
        int ctor2ParameterCount = ctor2.getGenericParameterTypes().length;

        int result = IntegerPolyfill.compare(ctor1ParameterCount, ctor2ParameterCount);
        if (result == 0) {
            return ctor1.toString().compareTo(ctor2.toString());
        }
        return result;
    }
}
