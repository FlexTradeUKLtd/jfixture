package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.intercept.TransformingBehaviour;
import com.flextrade.jfixture.utility.Transformer;

public class TranformingCustomisation<T> implements Customisation {

    private final Class<T> classToIntercept;
    private final Transformer<T> transformer;

    public TranformingCustomisation(Class<T> classToIntercept, Transformer<T> transformer) {
        this.classToIntercept = classToIntercept;
        this.transformer = transformer;
    }

    @Override
    public void customise(JFixture fixture) {
        fixture.behaviours().add(new TransformingBehaviour<T>(classToIntercept, transformer));
    }
}
