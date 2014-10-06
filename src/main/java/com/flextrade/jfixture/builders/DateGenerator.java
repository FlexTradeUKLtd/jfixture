package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.Clock;
import com.flextrade.jfixture.utility.TimeProvider;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Random;

@RequiredArgsConstructor
class DateGenerator implements SpecimenBuilder {

    private final Random random = new Random();
    private final TimeProvider timeProvider;

    public DateGenerator() {
        this(new Clock());
    }

    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!request.equals(Date.class)) {
            return new NoSpecimen();
        }

        long millisecondSinceEpoch = this.timeProvider.getCurrentTimeInMilliseconds();
        long randomMillisecondsUpToNow = (long) (random.nextDouble() * millisecondSinceEpoch);

        return new Date(randomMillisecondsUpToNow);
    }
}
