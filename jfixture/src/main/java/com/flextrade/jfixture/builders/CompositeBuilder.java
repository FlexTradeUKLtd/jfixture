package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;

import java.util.ArrayList;
import java.util.Arrays;

public class CompositeBuilder extends ArrayList<SpecimenBuilder> implements SpecimenBuilder {

    public CompositeBuilder(SpecimenBuilder... builders) {
        this(Arrays.asList(builders));
    }

    public CompositeBuilder(Iterable<SpecimenBuilder> builders) {
        for (SpecimenBuilder builder : builders) {
            this.add(builder);
        }
    }

    @Override
    public Object create(Object request, SpecimenContext context) {

        for (SpecimenBuilder builder : this) {
            Object result = builder.create(request, context);
            if (isSpecimen(result)) {
                return result;
            }
        }

        return new NoSpecimen();
    }

    private boolean isSpecimen(Object result) {
        return !(result instanceof NoSpecimen);
    }
}
