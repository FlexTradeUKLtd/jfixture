package com.flextrade.jfixture.utility;

import java.lang.reflect.Method;

public final class PropertyUtil {
    public static Boolean isMethodASetterProperty(Method method, Class clazz) {
        String name = method.getName();
        if (!name.startsWith("set")) return false;

        Class[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) return false;

        String propertyName = method.getName().substring(3);
        Method[] methodsInType = clazz.getMethods();
        for (Method m : methodsInType) {
            if (methodIsStandardSetter(propertyName, m) ||
                    methodIsBooleanSetter(propertyName, m)) {
                return true;
            }
        }

        return false;
    }

    public static String getMemberNameFromMethod(Method method) {
        String name = method.getName();
        if(name.startsWith("get") || name.startsWith("set")) {
            return name.substring(3);
        }

        if(name.startsWith("is") && Character.isUpperCase(name.charAt(2))) {
            return name.substring(2);
        }

        return name;
    }

    private static boolean methodIsStandardSetter(String propertyName, Method m) {
        return m.getName().equals("get" + propertyName);
    }

    private static boolean methodIsBooleanSetter(String propertyName, Method m) {
        return m.getName().equals("is" + propertyName) &&
                (m.getGenericReturnType().equals(Boolean.class) ||
                        m.getGenericReturnType().equals(boolean.class));
    }
}
