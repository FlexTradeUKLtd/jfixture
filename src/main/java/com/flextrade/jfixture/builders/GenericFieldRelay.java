package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.FieldRequest;
import com.flextrade.jfixture.requests.SeededRequest;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

class GenericFieldRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof FieldRequest)) {
            return new NoSpecimen();
        }

        FieldRequest fieldRequest = (FieldRequest) request;
        Field field = fieldRequest.getField();
        SpecimenType containingType = fieldRequest.getContainingType();

        Type parameterType;
        if(field.getType() != null && field.getGenericType() instanceof Class) {
            parameterType = field.getType();
        } else if(field.getType() != null && field.getGenericType() instanceof TypeVariable) {
            TypeVariable tv = (TypeVariable)field.getGenericType();
            String tvName = tv.getName();
            parameterType = containingType.getGenericTypeArguments().getType(tvName);
        } else if (containingType.getGenericTypeArguments().getLength() == 1) {
            // Won't work for multiple generic params
            parameterType = containingType.getGenericTypeArguments().get(0).getType();
        } else {
            return new NoSpecimen();
        }

        String fieldName = field.getName();
        SeededRequest seededRequest = new SeededRequest(fieldName, parameterType);

        return context.resolve(seededRequest);
    }
}
