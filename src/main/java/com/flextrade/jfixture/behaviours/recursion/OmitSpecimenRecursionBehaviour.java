package com.flextrade.jfixture.behaviours.recursion;

import com.flextrade.jfixture.FixtureBehaviour;
import com.flextrade.jfixture.SpecimenBuilder;

public class OmitSpecimenRecursionBehaviour implements FixtureBehaviour {
    @Override
    public SpecimenBuilder transform(SpecimenBuilder builder) {

        if (builder == null) {
            throw new IllegalArgumentException("builder");
        }

        return new RecursionGuard(builder, new OmitSpecimenRecursionHandler());
    }
}