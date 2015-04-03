package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.joda.time.Duration;
import org.joda.time.ReadableDuration;

public class ReadableDurationRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!request.equals(ReadableDuration.class))
            return new NoSpecimen();

        return context.resolve(Duration.class);
    }
}
