package com.flextrade.jfixture.utility;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ParameterUtils {

    public static List<Object> getConstructorArguments(Constructor constructor, SpecimenType specimenType, SpecimenContext context) {
        Type[] parameterTypes = getParameterTypes(constructor.getGenericParameterTypes(), specimenType);
        return convertTypesToInstances(parameterTypes ,context);
    }

    public static List<Object> getMethodParameters(Method method, SpecimenType specimenType, SpecimenContext context) {
        Type[] parameterTypes = getParameterTypes(method.getGenericParameterTypes(), specimenType);
        return convertTypesToInstances(parameterTypes, context);
    }

    private static List<Object> convertTypesToInstances(Type[] parameterTypes, SpecimenContext context) {
        List<Object> parameters = new ArrayList<Object>();

        for (Type parameterType : parameterTypes) {
            Object parameterInstance = context.resolve(parameterType);
            if (parameterInstance instanceof NoSpecimen)
                return null;

            parameters.add(parameterInstance);
        }

        return parameters;
    }

    private static Type[] getParameterTypes(Type[] originalGenericParameterTypes, SpecimenType specimenType) {
        Type[] safeParameterTypes = new Type[originalGenericParameterTypes.length];

        for (int i = 0; i < originalGenericParameterTypes.length; i++) {
            Type unsafeType = originalGenericParameterTypes[i];
            safeParameterTypes[i] = convertPossibleGenericTypeToSpecimenType(unsafeType, specimenType);
        }

        return safeParameterTypes;
    }

    public static SpecimenType convertPossibleGenericTypeToSpecimenType(Type originalType, final SpecimenType contextualType) {
      return SpecimenType.withGenericContext(originalType, contextualType);
    }
}
