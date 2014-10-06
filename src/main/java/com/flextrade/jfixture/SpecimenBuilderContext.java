package com.flextrade.jfixture;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class SpecimenBuilderContext implements SpecimenContext {

    private final SpecimenBuilder builder;

    @Override
    public Object resolve(Object request) {
        return this.builder.create(request, this);
    }
}
