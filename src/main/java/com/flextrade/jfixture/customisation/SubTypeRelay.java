package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class SubTypeRelay implements SpecimenBuilder {

    private final Class baseClass;
    private final Class subClass;

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!request.equals(baseClass)) {
            return new NoSpecimen();
        }

        return context.resolve(this.subClass);
    }
}
