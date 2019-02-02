package com.flextrade.jfixture.jodatime.component;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.SpecimenSupplier;
import com.flextrade.jfixture.jodatime.customisation.JodaTimeCustomisation;
import org.hamcrest.Matchers;
import org.joda.time.Chronology;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.MonthDay;
import org.joda.time.Months;
import org.joda.time.MutableDateTime;
import org.joda.time.MutableInterval;
import org.joda.time.MutablePeriod;
import org.joda.time.Partial;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.joda.time.TimeOfDay;
import org.joda.time.Weeks;
import org.joda.time.YearMonth;
import org.joda.time.YearMonthDay;
import org.joda.time.Years;
import org.joda.time.chrono.ISOChronology;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


public class TestAllClassDataTypesAreSupported {

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

    // Instants

    @Test
    public void creates_instance_of_DateMidnight() throws ParseException {
        DateMidnight dateMidnight = fixture.create(DateMidnight.class);
        assertThat(dateMidnight, notNullValue());
        assertThat(dateMidnight.toDate(), is(formatter.parse("2001/01/01 00:00:00")));
    }

    @Test
    public void creates_instance_of_DateTime() {
        DateTime dateTime = fixture.create(DateTime.class);
        assertThat(dateTime, notNullValue());
        assertThat(dateTime.toDate(), is(date));
    }

    @Test
    public void creates_instance_of_MutableDateTime() {
        MutableDateTime dateTime = fixture.create(MutableDateTime.class);
        assertThat(dateTime, notNullValue());
        assertThat(dateTime.toDate(), is(date));
    }

    // Durations

    @Test
    public void creates_instance_of_Duration() {
        Duration duration = fixture.create(Duration.class);
        assertThat(duration, notNullValue());
        assertThat(duration, is(Duration.standardDays(365)));
    }

    // Intervals

    @Test
    public void creates_instance_of_Interval() {
        Interval interval = fixture.create(Interval.class);
        assertThat(interval, notNullValue());
        assertThat(interval.getStart().toDate(), is(date));
        assertThat(interval.getEnd().toDate(), is(secondDate));
    }

    @Test
    public void creates_instance_of_MutableInterval() {
        MutableInterval interval = fixture.create(MutableInterval.class);
        assertThat(interval, notNullValue());
        assertThat(interval.getStart().toDate(), is(date));
        assertThat(interval.getEnd().toDate(), is(secondDate));
    }

    // Partials

    @Test
    public void creates_instance_of_LocalDate() throws ParseException {
        LocalDate localDate = fixture.create(LocalDate.class);
        assertThat(localDate, notNullValue());
        assertThat(localDate.getYear(), is(2001)); // LocalDate::toDate is not recommended
        assertThat(localDate.getMonthOfYear(), is(1));
        assertThat(localDate.getDayOfMonth(), is(1));
    }

    @Test
    public void creates_instance_of_LocalDateTime() {
        LocalDateTime localDateTime = fixture.create(LocalDateTime.class);
        assertThat(localDateTime, notNullValue());
        assertThat(localDateTime.getYear(), is(2001)); // LocalDateTime::toDate is not recommended
        assertThat(localDateTime.getMonthOfYear(), is(1));
        assertThat(localDateTime.getDayOfMonth(), is(1));
        assertThat(localDateTime.getHourOfDay(), is(12));
        assertThat(localDateTime.getMinuteOfHour(), is(34));
        assertThat(localDateTime.getSecondOfMinute(), is(56));
    }

    @Test
    public void creates_instance_of_LocalTime() {
        LocalTime localTime = fixture.create(LocalTime.class);
        assertThat(localTime, notNullValue());
        assertThat(localTime.getHourOfDay(), is(12));
        assertThat(localTime.getMinuteOfHour(), is(34));
        assertThat(localTime.getSecondOfMinute(), is(56));
    }

    @Test
    public void creates_instance_of_MonthDay() {
        MonthDay monthDay = fixture.create(MonthDay.class);
        assertThat(monthDay, notNullValue());
        assertThat(monthDay.getMonthOfYear(), is(1));
        assertThat(monthDay.getDayOfMonth(), is(1));
    }

    @Test
    public void creates_instance_of_Partial() {
        Partial partial = fixture.create(Partial.class);
        assertThat(partial, notNullValue());
        assertThat(partial.get(DateTimeFieldType.year()), is(2001));
        assertThat(partial.get(DateTimeFieldType.monthOfYear()), is(1));
        assertThat(partial.get(DateTimeFieldType.dayOfMonth()), is(1));
        assertThat(partial.get(DateTimeFieldType.hourOfDay()), is(12));
        assertThat(partial.get(DateTimeFieldType.minuteOfHour()), is(34));
        assertThat(partial.get(DateTimeFieldType.secondOfMinute()), is(56));
    }

    @Test
    public void creates_instance_of_TimeOfDay() {
        TimeOfDay timeOfDay = fixture.create(TimeOfDay.class);
        assertThat(timeOfDay, notNullValue());
        assertThat(timeOfDay.getHourOfDay(), is(12));
        assertThat(timeOfDay.getMinuteOfHour(), is(34));
        assertThat(timeOfDay.getSecondOfMinute(), is(56));
    }

    @Test
    public void creates_instance_of_YearMonth() {
        YearMonth yearMonth = fixture.create(YearMonth.class);
        assertThat(yearMonth, notNullValue());
        assertThat(yearMonth.getYear(), is(2001));
        assertThat(yearMonth.getMonthOfYear(), is(1));
    }

    @Test
    public void creates_instance_of_YearMonthDay() {
        YearMonthDay yearMonthDay = fixture.create(YearMonthDay.class);
        assertThat(yearMonthDay, notNullValue());
        assertThat(yearMonthDay, notNullValue());
        assertThat(yearMonthDay.getYear(), is(2001));
        assertThat(yearMonthDay.getMonthOfYear(), is(1));
        assertThat(yearMonthDay.getDayOfMonth(), is(1));
    }

    // Periods

    @Test
    public void creates_instance_of_Period() {
        Period period = fixture.create(Period.class);
        assertThat(period, notNullValue());
        assertThat(period, is(Period.hours(8760)));
    }

    @Test
    public void creates_instance_of_MutablePeriod() {
        MutablePeriod period = fixture.create(MutablePeriod.class);
        assertThat(period, notNullValue());
        assertThat(period, is(new MutablePeriod(8760, 0, 0, 0)));
    }

    @Test
    public void creates_instance_of_Days() {
        Days days = fixture.create(Days.class);
        assertThat(days, notNullValue());
        assertThat(days, is(Days.days(365)));
    }

    @Test
    public void creates_instance_of_Hours() {
        Hours hours = fixture.create(Hours.class);
        assertThat(hours, notNullValue());
        assertThat(hours, is(Hours.hours(8760)));
    }

    @Test
    public void creates_instance_of_Minutes() {
        Minutes minutes = fixture.create(Minutes.class);
        assertThat(minutes, notNullValue());
        assertThat(minutes, is(Minutes.minutes(525600)));
    }

    @Test
    public void creates_instance_of_Months() {
        Months months = fixture.create(Months.class);
        assertThat(months, notNullValue());
        assertThat(months, is(Months.months(12)));
    }

    @Test
    public void creates_instance_of_Seconds() {
        Seconds seconds = fixture.create(Seconds.class);
        assertThat(seconds, notNullValue());
        assertThat(seconds, is(Seconds.seconds(31536000)));
    }

    @Test
    public void creates_instance_of_Weeks() {
        Weeks weeks = fixture.create(Weeks.class);
        assertThat(weeks, notNullValue());
        assertThat(weeks, is(Weeks.weeks(52)));
    }

    @Test
    public void creates_instance_of_Years() {
        Years years = fixture.create(Years.class);
        assertThat(years, notNullValue());
        assertThat(years, is(Years.years(1)));
    }

    @Test
    public void creates_UTC_timezone_instance() {
        DateTimeZone tz = fixture.create(DateTimeZone.class);
        assertThat(tz, is(DateTimeZone.UTC));
    }

    @Test
    public void creates_ISO_chronology_instance() {
        Chronology chronology = fixture.create(Chronology.class);
        assertThat(chronology, Matchers.<Chronology>is(ISOChronology.getInstance()));
    }

    private void customiseToReturnFixedDates() throws ParseException {
        date = formatter.parse("2001/01/01 12:34:56");
        secondDate = formatter.parse("2002/01/01 12:34:56");
        fixture.customise().lazyInstance(Date.class, new FirstAndRestDateSpecimenSupplier(date, secondDate));
    }
}
