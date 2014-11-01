package com.flextrade.jfixture;

public interface SpecimenBuilder {
    Object create(Object request, SpecimenContext context);
}
