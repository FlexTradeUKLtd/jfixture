package com.flextrade.jfixture;

class SpecimenBuilderContext implements SpecimenContext {

    private final SpecimenBuilder builder;

    public SpecimenBuilderContext(SpecimenBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Object resolve(Object request) {
        return this.builder.create(request, this);
    }
}
