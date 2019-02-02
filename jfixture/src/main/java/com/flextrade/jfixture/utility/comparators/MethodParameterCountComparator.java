package com.flextrade.jfixture.utility.comparators;

import com.flextrade.jfixture.utility.IntegerPolyfill;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * Compare by method parameter count, but if they're equal compare by the actual parameter types.
 * This is necessary as {@link Class#getMethods()} has no guarantees on order,
 * and for reproducible tests the objects must be constructed in the same way every time a test is run.
 */
public class MethodParameterCountComparator implements Comparator<Method> {

    @Override
    public int compare(Method method1, Method method2) {
        int method1ParameterCount = method1.getGenericParameterTypes().length;
        int method2ParameterCount = method2.getGenericParameterTypes().length;

        int result = IntegerPolyfill.compare(method1ParameterCount, method2ParameterCount);
        if (result == 0) {
            return method1.toString().compareTo(method2.toString());
        }
        return result;
    }
}
