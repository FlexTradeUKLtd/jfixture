package com.flextrade.jfixture.specifications;

import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Type;

public class TypeInHierarchySpecification implements Specification {

    private final SpecimenType type;

    public TypeInHierarchySpecification(Type type) {
        this.type = SpecimenType.of(type);
    }

    @Override
    public boolean isSatisfiedBy(Object request) {
        if (!(request instanceof SpecimenType)) return false;

        SpecimenType requestType = (SpecimenType) request;
        return requestType.getRawType().isAssignableFrom(type.getRawType());
    }
}
