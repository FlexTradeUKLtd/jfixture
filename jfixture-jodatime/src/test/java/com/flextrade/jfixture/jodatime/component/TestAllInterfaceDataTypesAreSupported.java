package com.flextrade.jfixture.jodatime.component;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.SpecimenSupplier;
import com.flextrade.jfixture.jodatime.customisation.JodaTimeCustomisation;
import org.hamcrest.Matchers;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Duration;
import org.joda.time.MutablePeriod;
import org.joda.time.ReadWritableDateTime;
import org.joda.time.ReadWritableInstant;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadableInterval;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


public class TestAllInterfaceDataTypesAreSupported {

    private final JFixture fixture = new JFixture();
    private final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date date;
    private Date secondDate;

    @Before
    public void initialise() throws ParseException {
        // The Joda Time customisation should derive all values from the JDK Date class
        // which means respecting any overrides for Date. We can use this to our advantage
        // in these tests by fixing two dates and asserting correctness of Joda objects
        // as a function of these dates rather than rely on the default 'random' implementation
        customiseToReturnFixedDates();
        fixture.customise(new JodaTimeCustomisation());
    }

    @Test
    public void creates_instance_of_ReadableDateTime() throws ParseException {
        ReadableDateTime dateTime = fixture.create(ReadableDateTime.class);
        assertThat(dateTime, notNullValue());
        assertThat(new Date(dateTime.getMillis()), is(date));
    }

    @Test
    public void creates_instance_of_ReadWritableDateTime() throws ParseException {
        ReadWritableDateTime dateTime = fixture.create(ReadWritableDateTime.class);
        assertThat(dateTime, notNullValue());
        assertThat(new Date(dateTime.getMillis()), is(date));
    }

    @Test
    public void creates_instance_of_ReadableDuration() throws ParseException {
        ReadableDuration duration = fixture.create(ReadableDuration.class);
        assertThat(duration, notNullValue());
        assertThat(duration, Matchers.<ReadableDuration>is(Duration.standardDays(365)));
    }

    @Test
    public void creates_instance_of_ReadableInstant() throws ParseException {
        ReadableInstant instant = fixture.create(ReadableInstant.class);
        assertThat(instant, notNullValue());
        assertThat(new Date(instant.getMillis()), is(date));
    }

    @Test
    public void creates_instance_of_ReadWritableInstant() throws ParseException {
        ReadWritableInstant instant = fixture.create(ReadWritableInstant.class);
        assertThat(instant, notNullValue());
        assertThat(new Date(instant.getMillis()), is(date));
    }

    @Test
    public void creates_instance_of_ReadablePartial() throws ParseException {
        ReadablePartial partial = fixture.create(ReadablePartial.class);
        assertThat(partial, notNullValue());
        assertThat(partial.get(DateTimeFieldType.year()), is(2001));
        assertThat(partial.get(DateTimeFieldType.monthOfYear()), is(1));
        assertThat(partial.get(DateTimeFieldType.dayOfMonth()), is(1));
        assertThat(partial.get(DateTimeFieldType.hourOfDay()), is(12));
        assertThat(partial.get(DateTimeFieldType.minuteOfHour()), is(34));
        assertThat(partial.get(DateTimeFieldType.secondOfMinute()), is(56));
    }

    @Test
    public void creates_instance_of_ReadableInterval() throws ParseException {
        ReadableInterval interval = fixture.create(ReadableInterval.class);
        assertThat(interval, notNullValue());
        assertThat(interval.getStart().toDate(), is(date));
        assertThat(interval.getEnd().toDate(), is(secondDate));
    }

    @Test
    public void creates_instance_of_ReadWritableInterval() throws ParseException {
        ReadWritableInterval interval = fixture.create(ReadWritableInterval.class);
        assertThat(interval, notNullValue());
        assertThat(interval.getStart().toDate(), is(date));
        assertThat(interval.getEnd().toDate(), is(secondDate));
    }

    @Test
    public void creates_instance_of_ReadablePeriod() throws ParseException {
        ReadablePeriod period = fixture.create(ReadablePeriod.class);
        assertThat(period, notNullValue());
        assertThat(period, Matchers.<ReadablePeriod>is(new MutablePeriod(1,0,0,0,0,0,0,0))); // 1Yr
    }

    @Test
    public void creates_instance_of_ReadWritablePeriod() throws ParseException {
        ReadWritablePeriod period = fixture.create(ReadWritablePeriod.class);
        assertThat(period, notNullValue());
        assertThat(period, Matchers.<ReadablePeriod>is(new MutablePeriod(1,0,0,0,0,0,0,0))); // 1Yr
    }

    private void customiseToReturnFixedDates() throws ParseException {
        date = formatter.parse("2001/01/01 12:34:56");
        secondDate = formatter.parse("2002/01/01 12:34:56");

        fixture.customise().lazyInstance(Date.class, new SpecimenSupplier<Date>() {
            boolean isFirstCall = true;

            @Override
            public Date create() {
                if (isFirstCall) {
                    isFirstCall = false;
                    return date;
                }

                return secondDate;
            }
        });
    }
}
