package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.MultipleCount;
import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.MultipleRequest;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
class MultipleSpecimenRelay implements SpecimenBuilder {

    private final MultipleCount multipleCount;

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof MultipleRequest)) {
            return new NoSpecimen();
        }

        MultipleRequest multipleRequest = (MultipleRequest) request;
        return buildArrayList(multipleRequest, context);
    }

    private Object buildArrayList(MultipleRequest multipleRequest, SpecimenContext context) {
        ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < multipleCount.getCount(); i++) {
            Object innerRequest = multipleRequest.getInnerRequest();
            Object result = context.resolve(innerRequest);
            if (result instanceof NoSpecimen) {
                return new NoSpecimen();
            }

            list.add(result);
        }

        return list;
    }
}
