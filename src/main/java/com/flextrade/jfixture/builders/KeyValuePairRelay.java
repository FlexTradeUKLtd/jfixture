package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.KeyValueRequest;
import com.flextrade.jfixture.utility.KeyValuePair;

class KeyValuePairRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof KeyValueRequest)) {
            return new NoSpecimen();
        }

        KeyValueRequest kvRequest = (KeyValueRequest)request;
        Object keyResult = context.resolve(kvRequest.getKeyType());
        if(keyResult instanceof NoSpecimen)
            return new NoSpecimen();

        Object valueResult = context.resolve(kvRequest.getValueType());
        if(valueResult instanceof NoSpecimen)
            return new NoSpecimen();

        return new KeyValuePair(keyResult, valueResult);
    }
}
