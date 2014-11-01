package com.flextrade.jfixture;

class FinalBuilderContainer implements BuilderContainer {
    private SpecimenBuilder finalBuilder;
    private SpecimenBuilderContext context;

    public void setFinalBuilder(SpecimenBuilder finalBuilder) {
        this.finalBuilder = finalBuilder;
        this.context = new SpecimenBuilderContext(this.finalBuilder);
    }

    public SpecimenBuilder getBuilder() {
        return finalBuilder;
    }

    public SpecimenBuilderContext getContext() {
        return context;
    }
}
