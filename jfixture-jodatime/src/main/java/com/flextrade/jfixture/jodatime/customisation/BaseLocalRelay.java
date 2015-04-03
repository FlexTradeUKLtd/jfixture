package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;
import org.joda.time.base.BaseLocal;

import java.util.Date;

public class BaseLocalRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType type = (SpecimenType) request;
        if (!BaseLocal.class.isAssignableFrom(type.getRawType())) {
            return new NoSpecimen();
        }

        Date date = (Date) context.resolve(Date.class);
        long instant = date.getTime();

        try {
            return type.getRawType().getDeclaredConstructor(long.class).newInstance(instant);
        } catch (Exception e) {
            e.printStackTrace();
            return new NoSpecimen();
        }
    }
}