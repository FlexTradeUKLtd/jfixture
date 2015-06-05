package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.RangeRequest;
import com.flextrade.jfixture.utility.SpecimenType;

import java.util.Date;

class DateRangeRelay implements SpecimenBuilder {
    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof RangeRequest)) {
            return new NoSpecimen();
        }

        RangeRequest rangeRequest = (RangeRequest) request;
        if (!requestIsAMatch(rangeRequest)) {
            return new NoSpecimen();
        }

        return create(rangeRequest, context);
    }

    private Object create(RangeRequest request, SpecimenContext context) {
        NumberInRangeGenerator numberInRangeGenerator = getNumberInRangeGenerator(request);
        Long value = (Long)numberInRangeGenerator.create(Long.class, context);
        return new Date(value);
    }

    private boolean requestIsAMatch(RangeRequest request) {
        boolean isType = request.getRequest() instanceof SpecimenType;
        if (!isType) return false;

        SpecimenType type = (SpecimenType) request.getRequest();
        return type.getRawType().equals(Date.class) &&
                (request.getMin() instanceof Date || request.getMin() instanceof Long) &&
                (request.getMax() instanceof Date || request.getMax() instanceof Long);
    }

    private NumberInRangeGenerator getNumberInRangeGenerator(RangeRequest request) {
        Long min = getMinLong(request);
        Long max = getMaxLong(request);
        return new NumberInRangeGenerator(min, max);
    }

    private Long getMaxLong(RangeRequest request) {
        Long max;
        if(request.getMax() instanceof Long)
            max = (Long)request.getMax();
        else
            max = ((Date)request.getMax()).getTime();
        return max;
    }

    private Long getMinLong(RangeRequest request) {
        Long min;
        if(request.getMin() instanceof Long)
            min = (Long)request.getMin();
        else
            min = ((Date)request.getMin()).getTime();
        return min;
    }
}
