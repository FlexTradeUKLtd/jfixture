package com.flextrade.jfixture.utility.comparators;

import com.flextrade.jfixture.utility.IntegerPolyfill;

import java.lang.reflect.Method;
import java.util.Comparator;

// Duplicate code: ideally this would be compare(Executable, Executable),
// but that base class was introduced in Java 8, and the current build is Java 6.
@SuppressWarnings("Duplicates")
public class MethodParameterCountComparator implements Comparator<Method> {

    @Override
    public int compare(Method method1, Method method2) {
        int method1ParameterCount = method1.getGenericParameterTypes().length;
        int method2ParameterCount = method2.getGenericParameterTypes().length;

        int result = IntegerPolyfill.compare(method1ParameterCount, method2ParameterCount);
        if (result == 0) {
            // It is necessary to compare the signature as a disambiguation after the param count comparison,
            // because Class.getMethods() has no guarantees on order
            // and using this comparator in a stable sort could yield different results in different runs.
            return method1.toString().compareTo(method2.toString());
        }
        return result;
    }
}
