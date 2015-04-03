package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.joda.time.Partial;
import org.joda.time.ReadablePartial;

public class ReadablePartialRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!request.equals(ReadablePartial.class))
            return new NoSpecimen();

        return context.resolve(Partial.class);
    }
}
