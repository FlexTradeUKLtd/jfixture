package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.recursion.OmitSpecimenRecursionBehaviour;
import com.flextrade.jfixture.behaviours.recursion.ThrowingRecursionBehaviour;

public class OmitSpecimenRecursionCustomisation implements Customisation {
    @Override
    public void customise(JFixture fixture) {

        fixture.behaviours().remove(ThrowingRecursionBehaviour.class);

        OmitSpecimenRecursionBehaviour behaviour = new OmitSpecimenRecursionBehaviour();
        fixture.behaviours().add(behaviour);
    }
}
