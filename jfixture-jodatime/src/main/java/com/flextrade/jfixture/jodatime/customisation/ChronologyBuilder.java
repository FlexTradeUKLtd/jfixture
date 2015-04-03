package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.joda.time.Chronology;
import org.joda.time.chrono.ISOChronology;

public class ChronologyBuilder implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!request.equals(Chronology.class))
            return new NoSpecimen();

        return ISOChronology.getInstance();
    }
}
