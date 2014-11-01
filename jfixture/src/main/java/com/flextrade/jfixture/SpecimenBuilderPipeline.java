package com.flextrade.jfixture;

public interface SpecimenBuilderPipeline {
    public JFixture addBuilderToStartOfPipeline(SpecimenBuilder builder);
    public JFixture addBuilderToEndOfPipeline(SpecimenBuilder builder);
}