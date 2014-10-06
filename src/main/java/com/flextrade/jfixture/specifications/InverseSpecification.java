package com.flextrade.jfixture.specifications;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InverseSpecification implements Specification {

    private final Specification inner;

    @Override
    public boolean isSatisfiedBy(Object request) {
        return !this.inner.isSatisfiedBy(request);
    }
}
