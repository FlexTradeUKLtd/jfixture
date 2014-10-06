package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.RangeRequest;

import java.lang.reflect.Type;

class NumericRangeRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof RangeRequest)) {
            return new NoSpecimen();
        }

        RangeRequest rangeRequest = (RangeRequest) request;
        if (!requestIsForANumber(rangeRequest)) {
            return new NoSpecimen();
        }

        NumberInRangeGenerator numberInRangeGenerator = getNumberInRangeGenerator(rangeRequest);
        return numberInRangeGenerator.create(rangeRequest.getRequest(), context);
    }

    private boolean requestIsForANumber(RangeRequest rangeRequest) {
        boolean isType = rangeRequest.getRequest() instanceof Type;
        boolean isNumberRange =  rangeRequest.getMin() instanceof Number && rangeRequest.getMax() instanceof Number;

        return isType && isNumberRange;
    }

    private NumberInRangeGenerator getNumberInRangeGenerator(RangeRequest rr) {
        Long min = ((Number) rr.getMin()).longValue();
        Long max = ((Number) rr.getMax()).longValue();

        if (min >= max) {
            throw new ObjectCreationException("Minimum value in range must be less than maximum");
        }

        return new NumberInRangeGenerator(min, max);
    }
}
