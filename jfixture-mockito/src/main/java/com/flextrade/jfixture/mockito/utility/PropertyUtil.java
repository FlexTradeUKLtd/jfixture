package com.flextrade.jfixture.mockito.utility;

import java.lang.reflect.Method;

public final class PropertyUtil {
    public static Boolean isMethodAGetterProperty(Method method) {
        String name = method.getName();
        if (!name.startsWith("get")) return false;

        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 0) return false;

        return hasMatchingSetter(method);
    }

    private static Boolean hasMatchingSetter(Method method) {
        String propertyName = com.flextrade.jfixture.utility.PropertyUtil.getMemberNameFromMethod(method);
        Method[] allMethods = method.getDeclaringClass().getMethods();
        boolean hasSetter = false;
        for (Method m : allMethods) {
            if (m.getName().equals("set" + propertyName)) {
                hasSetter = true;
                break;
            }
        }

        return hasSetter;
    }
}
