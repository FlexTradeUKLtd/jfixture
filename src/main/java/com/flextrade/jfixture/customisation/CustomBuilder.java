package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.SpecimenSupplier;
import com.flextrade.jfixture.requests.SeededRequest;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Type;

class CustomBuilder<T> implements SpecimenBuilder {

    private final SpecimenType specimenType;
    private final SpecimenSupplier<? extends T> supplier;

    public CustomBuilder(Type specimenType, SpecimenSupplier<? extends T> supplier) {
        if (specimenType == null) throw new IllegalArgumentException("specimenType cannot be null");
        if (supplier == null) throw new IllegalArgumentException("supplier cannot be null");

        this.specimenType = SpecimenType.of(specimenType);
        this.supplier = supplier;
    }

    public CustomBuilder(SpecimenType specimenType, SpecimenSupplier<? extends T> supplier) {
        this.specimenType = specimenType;
        this.supplier = supplier;
    }

    @Override
    public Object create(Object request, SpecimenContext context) {
        // We don't want a seed to affect the
        // custom object that gets returned
        if(request instanceof SeededRequest) {
            SeededRequest sr = (SeededRequest)request;
            request = sr.getRequest();
        }

        if (!(request instanceof Type)) {
            return new NoSpecimen();
        }

        Type type = (Type)request;

        // should be isAssignableFrom ?
        if (!this.specimenType.equals(type)) {
            return new NoSpecimen();
        }

        return this.supplier.create();
    }
}
