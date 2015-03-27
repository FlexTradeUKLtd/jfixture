package com.flextrade.jfixture.mockito.customisation;

import com.flextrade.jfixture.behaviours.autoproperty.AutoPropertyBehaviour;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.customisation.Customisation;

public class MockitoCustomisation implements Customisation{

    @Override
    public void customise(JFixture fixture) {
        AutoPropertyBehaviour autoPropertyBehaviour = fixture.behaviours().find(AutoPropertyBehaviour.class);
        if(autoPropertyBehaviour == null) return;

        autoPropertyBehaviour.specifications().add(new MockitoAutoPropertySpecification());
        fixture.addBuilderToEndOfPipeline(new MockitoRelay());
    }
}
