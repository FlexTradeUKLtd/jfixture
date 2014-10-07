package com.flextrade.jfixture.specifications;

public class InverseSpecification implements Specification {

    private final Specification inner;

    public InverseSpecification(Specification inner) {
        this.inner = inner;
    }

    @Override
    public boolean isSatisfiedBy(Object request) {
        return !this.inner.isSatisfiedBy(request);
    }
}
