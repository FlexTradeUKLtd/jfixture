package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;
import org.joda.time.base.BaseDateTime;

import java.util.Date;

public class BaseDateTimeRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType type = (SpecimenType) request;
        if (!BaseDateTime.class.isAssignableFrom(type.getRawType())) {
            return new NoSpecimen();
        }

        try {
            Date date = (Date) context.resolve(Date.class);
            long instant = date.getTime();
            return type.getRawType().getDeclaredConstructor(long.class).newInstance(instant);
        } catch (Exception e) {
            e.printStackTrace();
            return new NoSpecimen();
        }
    }
}