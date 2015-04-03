package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;
import org.joda.time.Interval;
import org.joda.time.base.BaseDuration;

public class BaseDurationRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType<?> type = (SpecimenType) request;
        if (!BaseDuration.class.isAssignableFrom(type.getRawType())) {
            return new NoSpecimen();
        }

        // There's only one implementation of BaseDuration so we're safe to do this
        Interval interval = (Interval) context.resolve(Interval.class);
        return interval.toDuration();
    }
}