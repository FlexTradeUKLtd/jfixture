package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.SeededRequest;

class SeededStringBuilder implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if(!(request instanceof SeededRequest))
            return new NoSpecimen();

        SeededRequest seededRequest = (SeededRequest)request;
        if(!(seededRequest.getSeed() instanceof String))
            return new NoSpecimen();

        if(!seededRequest.getRequest().equals(String.class))
            return new NoSpecimen();

        String seed = (String)seededRequest.getSeed();
        Object innerRequest = seededRequest.getRequest();

        Object specimen = context.resolve(innerRequest);
        if(!(specimen instanceof String))
            return new NoSpecimen();

        return seed + specimen;
    }
}
