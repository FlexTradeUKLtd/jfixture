package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.joda.time.DateTimeZone;

public class DateTimeZoneBuilder implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request.equals(DateTimeZone.class)))
            return new NoSpecimen();

        return DateTimeZone.UTC;
    }
}
