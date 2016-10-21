package com.flextrade.jfixture;

import com.flextrade.jfixture.utility.ParameterUtils;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DefaultFactoryMethodQuery implements FactoryMethodQuery {

    private final Comparator<Method> comparator;

    public DefaultFactoryMethodQuery() {
        this.comparator = null;
    }

    public DefaultFactoryMethodQuery(Comparator<Method> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<Method> getFactoryMethodsForType(SpecimenType type) {

        List<Method> factoryMethods = new ArrayList<Method>();
        Method[] allMethods = type.getRawType().getMethods();
        for (Method method : allMethods) {
            if (isStatic(method) && returnTypeIsAssignable(method, type) && !isCopyConstructorMethod(type, method))
                factoryMethods.add(method);
        }

        if (this.comparator != null) {  // Don't attempt to sort if there's no Comparator
            Collections.sort(factoryMethods, this.comparator);
        }

        return factoryMethods;
    }

    private boolean isCopyConstructorMethod(SpecimenType type, Method method) {
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        if (genericParameterTypes.length == 1) {
            SpecimenType returnType = ParameterUtils.convertPossibleGenericTypeToSpecimenType(method.getGenericReturnType(), type);
            SpecimenType typeOfSingletonParameter = ParameterUtils.convertPossibleGenericTypeToSpecimenType(genericParameterTypes[0], type);
            return typeOfSingletonParameter.getRawType().isAssignableFrom(returnType.getRawType());
        } else {
            return false;
        }
    }

    private static boolean returnTypeIsAssignable(Method method, SpecimenType type) {
        SpecimenType returnType = ParameterUtils.convertPossibleGenericTypeToSpecimenType(method.getGenericReturnType(), type);
        return type.getRawType().isAssignableFrom(returnType.getRawType());
    }

    private boolean isStatic(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }


}