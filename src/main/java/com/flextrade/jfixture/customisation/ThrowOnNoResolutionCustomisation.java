package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.noresolution.IgnoreNoResolutionBehaviour;
import com.flextrade.jfixture.behaviours.noresolution.ThrowOnNoResolutionBehaviour;

public class ThrowOnNoResolutionCustomisation implements Customisation {
    @Override
    public void customise(JFixture fixture) {
        fixture.behaviours().remove(IgnoreNoResolutionBehaviour.class);
        fixture.behaviours().add(new ThrowOnNoResolutionBehaviour());
    }
}
