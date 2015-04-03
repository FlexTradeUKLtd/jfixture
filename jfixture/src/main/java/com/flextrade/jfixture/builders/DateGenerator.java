package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.Clock;
import com.flextrade.jfixture.utility.FixtureMath;
import com.flextrade.jfixture.utility.TimeProvider;

import java.util.Calendar;
import java.util.Date;

class DateGenerator implements SpecimenBuilder {

    private final TimeProvider timeProvider;

    public DateGenerator() {
        this(new Clock());
    }

    public DateGenerator(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!request.equals(Date.class)) {
            return new NoSpecimen();
        }

        Date nowDate = new Date(this.timeProvider.getCurrentTimeInMilliseconds());
        long min = getTwoYearsAgoMs(nowDate);
        long max = getTwoYearsAheadMs(nowDate);

        long date = FixtureMath.randLong(min, max);
        return new Date(date);
    }

    private long getTwoYearsAheadMs(Date now) {
        Calendar twoYearsAhead = Calendar.getInstance();
        twoYearsAhead.setTime(now);
        twoYearsAhead.add(Calendar.YEAR, 2);
        return twoYearsAhead.getTimeInMillis();
    }

    private long getTwoYearsAgoMs(Date now) {
        Calendar twoYearsAgo = Calendar.getInstance();
        twoYearsAgo.setTime(now);
        twoYearsAgo.add(Calendar.YEAR, -2);
        return twoYearsAgo.getTimeInMillis();
    }
}
