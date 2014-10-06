package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.FactoryMethodRequest;
import com.flextrade.jfixture.utility.ParameterUtils;

import java.lang.reflect.Method;
import java.util.List;

class FactoryMethodRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if(!(request instanceof FactoryMethodRequest)) {
            return new NoSpecimen();
        }

        FactoryMethodRequest factoryMethodRequest = (FactoryMethodRequest)request;
        Method method = factoryMethodRequest.getMethod();

        List<Object> parameters = ParameterUtils.getMethodParameters(method, factoryMethodRequest.getContainingType(), context);
        if(parameters == null) {
            return new NoSpecimen();
        }

        try {
            Object[] paramsArray = parameters.toArray(new Object[parameters.size()]);
            return method.invoke(null, paramsArray);
        } catch (Exception e) {
            throw new ObjectCreationException(String.format("Unable to invoke factory method %s", method), e);
        }
    }
}
