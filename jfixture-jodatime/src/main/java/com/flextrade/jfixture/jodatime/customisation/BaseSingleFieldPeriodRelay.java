package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.joda.time.base.BaseSingleFieldPeriod;

public class BaseSingleFieldPeriodRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType type = (SpecimenType) request;
        if (!BaseSingleFieldPeriod.class.isAssignableFrom(type.getRawType())) {
            return new NoSpecimen();
        }

        Duration duration = (Duration) context.resolve(Duration.class);
        if (type.equals(Seconds.class)) return Seconds.seconds(Math.max(1, (int) duration.getStandardSeconds()));
        if (type.equals(Minutes.class)) return Minutes.minutes(Math.max(1, (int) duration.getStandardMinutes()));
        if (type.equals(Hours.class)) return Hours.hours(Math.max(1, (int) duration.getStandardHours()));

        if (type.equals(Days.class)) return Days.days(Math.max(1, (int) duration.getStandardDays()));
        if (type.equals(Weeks.class)) return Weeks.weeks(Math.max(1, (int) duration.getStandardDays() / 7));
        if (type.equals(Months.class)) return Months.months(Math.max(1, (int) duration.getStandardDays() / 30));
        if (type.equals(Years.class)) return Years.years(Math.max(1, (int) duration.getStandardDays() / 365));

        return new NoSpecimen();
    }
}