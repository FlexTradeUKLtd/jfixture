package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.FieldRequest;
import com.flextrade.jfixture.requests.SeededRequest;
import com.flextrade.jfixture.utility.ParameterUtils;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

class GenericFieldRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof FieldRequest)) {
            return new NoSpecimen();
        }

        FieldRequest genericTypeRequest = (FieldRequest) request;
        SpecimenType contextualType = genericTypeRequest.getContainingType();
        Field field = genericTypeRequest.getField();

        Type parameterType = getMethodReturnType(field, contextualType);
        String fieldName = field.getName();
        SeededRequest seededRequest = new SeededRequest(fieldName, parameterType);

        return context.resolve(seededRequest);
    }

    private Type getMethodReturnType(Field field, SpecimenType contextualType) {
        return ParameterUtils.convertPossibleGenericTypeToSpecimenType(field.getGenericType(), contextualType);
    }
}
