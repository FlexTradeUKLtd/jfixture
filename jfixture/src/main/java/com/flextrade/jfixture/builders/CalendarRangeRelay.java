package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.RangeRequest;
import com.flextrade.jfixture.utility.SpecimenType;

import java.util.Calendar;

class CalendarRangeRelay implements SpecimenBuilder {
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
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(value);
        return calendar;
    }

    private boolean requestIsAMatch(RangeRequest request) {
        boolean isType = request.getRequest() instanceof SpecimenType;
        if (!isType) return false;

        SpecimenType type = (SpecimenType) request.getRequest();
        return type.getRawType().equals(Calendar.class) &&
               request.getMin() instanceof Calendar &&
               request.getMax() instanceof Calendar;
    }

    private NumberInRangeGenerator getNumberInRangeGenerator(RangeRequest request) {
        Long min = ((Calendar) request.getMin()).getTimeInMillis();
        Long max = ((Calendar) request.getMax()).getTimeInMillis();

        return new NumberInRangeGenerator(min, max);
    }
}
