package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.GenericConstructorRequest;
import com.flextrade.jfixture.utility.ParameterUtils;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Constructor;
import java.util.List;

class GenericConstructorRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof GenericConstructorRequest)) {
            return new NoSpecimen();
        }

        GenericConstructorRequest genericConstructorRequest = (GenericConstructorRequest) request;
        Constructor constructor = genericConstructorRequest.getConstructor();
        SpecimenType specimenType = genericConstructorRequest.getContainingType();

        List<Object> parameters = ParameterUtils.getConstructorArguments(constructor, specimenType, context);

        if (parameters == null) {
            return new NoSpecimen();
        }

        try {
            Object[] paramsArray = parameters.toArray(new Object[parameters.size()]);

            return constructor.newInstance(paramsArray);
        } catch (Exception e) {
            throw new ObjectCreationException(String.format("Unable to invoke constructor %s", constructor), e);
        }
    }


}
