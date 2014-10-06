package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.recursion.OmitSpecimenRecursionBehaviour;
import com.flextrade.jfixture.behaviours.recursion.ThrowingRecursionBehaviour;

public class ThrowingRecursionCustomisation implements Customisation {
    @Override
    public void customise(JFixture fixture) {

        fixture.behaviours().remove(OmitSpecimenRecursionBehaviour.class);

        ThrowingRecursionBehaviour behaviour = new ThrowingRecursionBehaviour();
        fixture.behaviours().add(behaviour);
    }
}