package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Partial;

public class PartialRelay implements SpecimenBuilder {

    private final DateTimeFieldType[] fieldTypes = new DateTimeFieldType[]{
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
            DateTimeFieldType.hourOfDay(),
            DateTimeFieldType.minuteOfHour(),
            DateTimeFieldType.secondOfMinute()};

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!request.equals(Partial.class))
            return new NoSpecimen();

        DateTime date = (DateTime) context.resolve(DateTime.class);

        return new Partial(fieldTypes, new int[]{
                date.getYear(),
                date.getMonthOfYear(),
                date.getDayOfMonth(),
                date.getHourOfDay(),
                date.getMinuteOfHour(),
                date.getSecondOfMinute()});
    }
}