package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.MethodRequest;
import com.flextrade.jfixture.requests.SeededRequest;
import com.flextrade.jfixture.utility.GenericTypeCollection;
import com.flextrade.jfixture.utility.PropertyUtil;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

class GenericMethodBuilder implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof MethodRequest)) {
            return new NoSpecimen();
        }

        MethodRequest genericTypeRequest = (MethodRequest) request;
        GenericTypeCollection genericTypes = genericTypeRequest.getContainingType().getGenericTypeArguments();
        Method method = genericTypeRequest.getMethod();

        Type parameterType = null;
        if (method.getGenericParameterTypes().length == 1) {
            final Type genericType = method.getGenericParameterTypes()[0];
            if(genericType instanceof Class) {
                parameterType = genericType;
            } else if(genericType instanceof ParameterizedType) {
                parameterType = SpecimenType.of(genericType);
            }
            else if (genericType instanceof TypeVariable) {
                parameterType = genericTypes.getType(genericType.toString());
            }

        } else {
            return new NoSpecimen();
        }

        String propertyName = PropertyUtil.getMemberNameFromMethod(method);
        SeededRequest seededRequest = new SeededRequest(propertyName, parameterType);

        return context.resolve(seededRequest);
    }
}
