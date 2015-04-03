package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.base.BaseInterval;

import java.lang.reflect.Constructor;

public class BaseIntervalRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType type = (SpecimenType) request;
        if (!BaseInterval.class.isAssignableFrom(type.getRawType())) {
            return new NoSpecimen();
        }

        DateTime dateA = (DateTime) context.resolve(DateTime.class);
        DateTime dateB = (DateTime) context.resolve(DateTime.class);

        try {
            Constructor constructor = type.getRawType().getDeclaredConstructor(ReadableInstant.class, ReadableInstant.class);

            Object specimen;
            if (dateA.isBefore(dateB))
                specimen = constructor.newInstance(dateA, dateB);
            else
                specimen = constructor.newInstance(dateB, dateA);

            return specimen;

        } catch (Exception e) {
            e.printStackTrace();
            return new NoSpecimen();
        }
    }
}