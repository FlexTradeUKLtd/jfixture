package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.joda.time.Interval;
import org.joda.time.MutableInterval;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadableInterval;

public class ReadableIntervalRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request.equals(ReadableInterval.class) || request.equals(ReadWritableInterval.class)))
            return new NoSpecimen();

        Interval interval = (Interval) context.resolve(Interval.class);
        return new MutableInterval(interval);
    }
}