package com.flextrade.jfixture.utility.comparators;

import java.lang.reflect.Method;
import java.util.Comparator;

public class MethodParameterCountComparator implements Comparator<Method> {

    @Override
    public int compare(Method method1, Method method2) {
        Integer method1Parameters = method1.getGenericParameterTypes().length;
        Integer method2Parameters = method2.getGenericParameterTypes().length;

        return method1Parameters.compareTo(method2Parameters);
    }
}
