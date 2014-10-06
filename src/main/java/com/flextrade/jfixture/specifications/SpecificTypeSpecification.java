package com.flextrade.jfixture.specifications;

import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Type;

public class SpecificTypeSpecification implements Specification {

    private final SpecimenType type;

    public SpecificTypeSpecification(Type type) {
        this.type = SpecimenType.of(type);
    }

    @Override
    public boolean isSatisfiedBy(Object request) {
        return type.equals(request);
    }
}
