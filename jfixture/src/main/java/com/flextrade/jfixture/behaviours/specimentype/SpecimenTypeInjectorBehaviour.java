package com.flextrade.jfixture.behaviours.specimentype;

import com.flextrade.jfixture.FixtureBehaviour;
import com.flextrade.jfixture.SpecimenBuilder;

public class SpecimenTypeInjectorBehaviour implements FixtureBehaviour {
    @Override
    public SpecimenBuilder transform(SpecimenBuilder builder) {
        return new SpecimenTypeInjector(builder);
    }
}
