package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.base.BasePeriod;

public class BasePeriodRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType<?> type = (SpecimenType) request;
        if (!BasePeriod.class.isAssignableFrom(type.getRawType())) {
            return new NoSpecimen();
        }

        Duration duration = (Duration) context.resolve(Duration.class);
        Period period = duration.toPeriod();

        try {
            return type.getRawType().getDeclaredConstructor(Object.class).newInstance(period);
        } catch (Exception e) {
            e.printStackTrace();
            return new NoSpecimen();
        }
    }
}
