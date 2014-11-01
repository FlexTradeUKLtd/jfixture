package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.autoproperty.AutoPropertyBehaviour;

public class AutoPropertyCustomisation implements Customisation {
    @Override
    public void customise(JFixture fixture) {

        if (fixture.behaviours().find(AutoPropertyBehaviour.class) == null) {
            fixture.behaviours().add(new AutoPropertyBehaviour());
        }
    }
}
