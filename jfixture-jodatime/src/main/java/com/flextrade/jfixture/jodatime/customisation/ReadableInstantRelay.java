package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadWritableDateTime;
import org.joda.time.ReadWritableInstant;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;

public class ReadableInstantRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request.equals(ReadableInstant.class) ||
                request.equals(ReadWritableInstant.class) ||
                request.equals(ReadableDateTime.class) ||
                request.equals(ReadWritableDateTime.class))) {
            return new NoSpecimen();
        }

        DateTime date = (DateTime) context.resolve(DateTime.class);
        return new MutableDateTime(date);
    }
}
