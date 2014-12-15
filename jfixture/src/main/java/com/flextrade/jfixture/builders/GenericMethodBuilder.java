package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.MethodRequest;
import com.flextrade.jfixture.requests.SeededRequest;
import com.flextrade.jfixture.utility.ParameterUtils;
import com.flextrade.jfixture.utility.PropertyUtil;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

class GenericMethodBuilder implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof MethodRequest)) {
            return new NoSpecimen();
        }

        MethodRequest genericTypeRequest = (MethodRequest) request;
        SpecimenType contextualType = genericTypeRequest.getContainingType();
        Method method = genericTypeRequest.getMethod();

        Type parameterType = getMethodReturnType(method, contextualType);
        String propertyName = PropertyUtil.getMemberNameFromMethod(method);
        SeededRequest seededRequest = new SeededRequest(propertyName, parameterType);

        return context.resolve(seededRequest);
    }

    private Type getMethodReturnType(Method method, SpecimenType contextualType) {
        Type originalReturnType = method.getGenericParameterTypes()[0]; // Previous checks ensure we will have only one value
        return ParameterUtils.convertPossibleGenericTypeToSpecimenType(originalReturnType, contextualType);
    }
}
