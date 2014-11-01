package com.flextrade.jfixture.specifications;

import java.lang.reflect.Type;

public class TypeRequestSpecification implements Specification {
    @Override
    public boolean isSatisfiedBy(Object request) {
        return request instanceof Type;
    }
}
