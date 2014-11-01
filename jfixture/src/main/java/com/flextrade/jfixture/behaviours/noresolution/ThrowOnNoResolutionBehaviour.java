package com.flextrade.jfixture.behaviours.noresolution;

import com.flextrade.jfixture.FixtureBehaviour;
import com.flextrade.jfixture.SpecimenBuilder;

public class ThrowOnNoResolutionBehaviour implements FixtureBehaviour {
    @Override
    public SpecimenBuilder transform(SpecimenBuilder builder) {

        if (builder == null) throw new IllegalArgumentException();

        return new NoResolutionGuard(builder, new ThrowingNoResolutionHandler());
    }
}
