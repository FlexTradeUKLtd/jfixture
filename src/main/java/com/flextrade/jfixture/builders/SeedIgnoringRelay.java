package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.SeededRequest;

class SeedIgnoringRelay implements SpecimenBuilder {
    @Override
    public Object create(Object request, SpecimenContext context) {
        if(!(request instanceof SeededRequest))
            return new NoSpecimen();

        SeededRequest seededRequest = (SeededRequest)request;
        Object innerRequest = seededRequest.getRequest();
        Object specimen = context.resolve(innerRequest);

        if(specimen instanceof NoSpecimen)
            return new NoSpecimen();

        return specimen;
    }
}
