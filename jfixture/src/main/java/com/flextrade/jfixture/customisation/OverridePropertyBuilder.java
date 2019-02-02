package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.MethodRequest;
import com.flextrade.jfixture.utility.PrimitiveTypeMap;
import com.flextrade.jfixture.utility.PropertyUtil;

import java.lang.reflect.Method;

public class OverridePropertyBuilder implements SpecimenBuilder {

    private final Class clazz;
    private final String propertyName;
    private final Object propertyValue;

    OverridePropertyBuilder(Class clazz, String propertyName, Object propertyValue) {
        this.clazz = clazz;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof MethodRequest)) {
            return new NoSpecimen();
        }

        MethodRequest methodRequest = (MethodRequest)request;
        //noinspection EqualsBetweenInconvertibleTypes SpecimenType knows how to do equals(Class<?>)
        if(!methodRequest.getContainingType().equals(this.clazz)) {
            return new NoSpecimen();
        }

        if(!PropertyUtil.isMethodASetterProperty(methodRequest.getMethod(), methodRequest.getContainingType().getRawType())) {
            return new NoSpecimen();
        }

        if(!methodNameMatchesOverriddenProperty(methodRequest.getMethod(), this.propertyName)) {
            return new NoSpecimen();
        }

        Class<?> propertyValueClass = nonPrimitiveType(propertyValue.getClass());
        Class<?> methodParamClass = nonPrimitiveType(methodRequest.getMethod().getParameterTypes()[0]);

        boolean isAssignable = methodParamClass.isAssignableFrom(propertyValueClass);
        if(!isAssignable) { // Don't return NoSpecimen here because it's almost certainly a user error so it shouldn't be ignored
            throw new ObjectCreationException(
                    "The property " + methodRequest.getMethod() + " has been overridden with an instance that does not match the expected type.\n" +
                    "The type of the property is " + methodParamClass + " but the overriding object is of type " + propertyValueClass);
        }

        return propertyValue;
    }

    private static Class nonPrimitiveType(Class clazz) {
        if(!(clazz.isPrimitive())) return clazz;
        return PrimitiveTypeMap.map.get(clazz);
    }

    private static boolean methodNameMatchesOverriddenProperty(Method method, String propertyName) {
        if(method.getName().equalsIgnoreCase(propertyName)) return true; // e.g. propertyName = "setSize"

        String methodPropertyName = PropertyUtil.getMemberNameFromMethod(method); // e.g. propertyName = "size"
        return methodPropertyName.equalsIgnoreCase(propertyName);
    }
}
