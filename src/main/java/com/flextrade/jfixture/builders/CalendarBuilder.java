package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

class CalendarBuilder implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!request.equals(Calendar.class)) {
            return new NoSpecimen();
        }

        Object dateResult = context.resolve(Date.class);
        if (dateResult instanceof NoSpecimen) {
            return new NoSpecimen();
        }

        Date date = (Date) dateResult;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
