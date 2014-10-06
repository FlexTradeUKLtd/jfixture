package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.SeededRequest;
import com.flextrade.jfixture.requests.enrichers.RequestEnricher;
import com.flextrade.jfixture.utility.SpecimenType;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

@RequiredArgsConstructor
class FieldRelay implements SpecimenBuilder {

    private final RequestEnricher requestEnricher;

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof Field)) {
            return new NoSpecimen();
        }

        Field field = (Field) request;
        Type genericType = field.getGenericType();

        SpecimenType specimenType = SpecimenType.of(genericType);
        String fieldName = field.getName();

        Object actualRequest = getActualRequest(field, specimenType);
        return context.resolve(new SeededRequest(fieldName, actualRequest));
    }

    private Object getActualRequest(Field field, SpecimenType specimenType) {
        Object actualRequest = specimenType;
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            Object newRequest = requestEnricher.enrich(specimenType, annotation);
            if (newRequest != null) {
                actualRequest = newRequest;
                break;
            }
        }
        return actualRequest;
    }
}
