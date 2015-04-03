package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.joda.time.Duration;
import org.joda.time.MutablePeriod;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class ReadablePeriodRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request.equals(ReadablePeriod.class) || request.equals(ReadWritablePeriod.class)))
            return new NoSpecimen();

        Duration duration = (Duration) context.resolve(Duration.class);
        return new MutablePeriod(duration);
    }
}
