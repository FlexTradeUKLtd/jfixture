package com.flextrade.jfixture.specifications;

public class NeverSpecification implements Specification {
    @Override
    public boolean isSatisfiedBy(Object request) {
        return false;
    }
}
