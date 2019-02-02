package com.flextrade.jfixture;

public interface SpecimenBuilderPipeline {
    JFixture addBuilderToStartOfPipeline(SpecimenBuilder builder);
    JFixture addBuilderToEndOfPipeline(SpecimenBuilder builder);
}
