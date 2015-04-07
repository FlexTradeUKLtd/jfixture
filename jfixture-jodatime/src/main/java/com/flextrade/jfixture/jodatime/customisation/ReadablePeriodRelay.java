package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.joda.time.DateTime;
import org.joda.time.MutablePeriod;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class ReadablePeriodRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request.equals(ReadablePeriod.class) || request.equals(ReadWritablePeriod.class)))
            return new NoSpecimen();

        DateTime dateA = (DateTime) context.resolve(DateTime.class);
        DateTime dateB = (DateTime) context.resolve(DateTime.class);

        if (dateA.isBefore(dateB))
            return new MutablePeriod(dateA, dateB);
        else
            return new MutablePeriod(dateB, dateA);
    }
}
