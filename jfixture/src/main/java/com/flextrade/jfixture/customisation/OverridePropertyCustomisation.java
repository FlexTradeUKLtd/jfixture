package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;

public class OverridePropertyCustomisation implements Customisation {

    private final Class clazz;
    private final String propertyName;
    private final Object propertyValue;

    public OverridePropertyCustomisation(Class clazz, String propertyName, Object propertyValue) {

        this.clazz = clazz;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    @Override
    public void customise(JFixture fixture) {
        fixture.addBuilderToStartOfPipeline(new OverridePropertyBuilder(this.clazz, this.propertyName, this.propertyValue));
    }
}
