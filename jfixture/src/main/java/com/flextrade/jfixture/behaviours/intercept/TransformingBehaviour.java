package com.flextrade.jfixture.behaviours.intercept;

import com.flextrade.jfixture.FixtureBehaviour;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.utility.Transformer;

public class TransformingBehaviour<T> implements FixtureBehaviour {

    private final Class<T> classToIntercept;
    private final Transformer<T> transformer;

    public TransformingBehaviour(Class<T> classToIntercept, Transformer<T> transformer) {
        this.classToIntercept = classToIntercept;
        this.transformer = transformer;
    }

    @Override
    public SpecimenBuilder transform(SpecimenBuilder builder) {
        return new TransformerBuilder<T>(builder, classToIntercept, transformer);
    }
}
