package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;

class SubTypeRelay implements SpecimenBuilder {

    private final Class baseClass;
    private final Class subClass;

    public SubTypeRelay(Class baseClass, Class subClass) {
        this.baseClass = baseClass;
        this.subClass = subClass;
    }

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!request.equals(baseClass)) {
            return new NoSpecimen();
        }

        return context.resolve(this.subClass);
    }
}
