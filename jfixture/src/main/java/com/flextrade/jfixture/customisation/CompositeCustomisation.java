package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;

import java.util.Arrays;

public class CompositeCustomisation implements Customisation {

    private final Iterable<Customisation> customisations;

    public CompositeCustomisation(Customisation... customisations) {
        this(Arrays.asList(customisations));
    }

    public CompositeCustomisation(Iterable<Customisation> customisations) {
        this.customisations = customisations;
    }

    @Override
    public void customise(JFixture fixture) {
        for (Customisation c : customisations) {
            fixture.customise(c);
        }
    }
}
