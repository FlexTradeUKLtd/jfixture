package com.flextrade.jfixture.specifications;

public class AlwaysSpecification implements Specification {
    @Override
    public boolean isSatisfiedBy(Object request) {
        return true;
    }
}
