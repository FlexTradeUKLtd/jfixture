package component.extensions;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.exceptions.InvalidRequestException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class TestCreateInRange {

    @Test
    public void longs_within_min_and_max_values_are_generated() {
        JFixture fixture = new JFixture();
        Long value = fixture.create().inRange(Long.class, 1000L, 2000L);

        assertThat(value, greaterThanOrEqualTo(1000L));
        assertThat(value, lessThan(2000L));
    }

    @Test
    public void doubles_within_min_and_max_values_are_generated() {
        JFixture fixture = new JFixture();
        Double value = fixture.create().inRange(Double.class, 1000.0, 2000.0);

        assertThat(value, greaterThanOrEqualTo(1000.0));
        assertThat(value, lessThan(2000.0));
    }

    @Test
    public void dates_within_min_and_max_values_are_generated() throws ParseException {
        JFixture fixture = new JFixture();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date min = dateFormat.parse("2000-01-01");
        Date max = dateFormat.parse("2000-12-31");

        Date date = fixture.create().inRange(Date.class, min, max);

        assertThat(date, greaterThanOrEqualTo(min));
        assertThat(date, lessThan(max));
    }

    @Test // We want it to work for Dates but with Long min and max if used with @Range annotation
    public void dates_within_min_and_max_long_values_are_generated() throws ParseException {
        JFixture fixture = new JFixture();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date min = dateFormat.parse("2000-01-01");
        Date max = dateFormat.parse("2000-12-31");

        Date date = fixture.create().inRange(Date.class, min.getTime(), max.getTime());

        assertThat(date, greaterThanOrEqualTo(min));
        assertThat(date, lessThan(max));
    }

    @Test
    public void calendars_within_min_and_max_values_are_generated() throws ParseException {
        JFixture fixture = new JFixture();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar min = Calendar.getInstance();
        min.setTime(dateFormat.parse("2000-01-01"));
        Calendar max = Calendar.getInstance();
        max.setTime(dateFormat.parse("2000-12-30"));

        Calendar date = fixture.create().inRange(Calendar.class, min, max);

        assertThat(date, greaterThanOrEqualTo(min));
        assertThat(date, lessThan(max));
    }

    @Test(expected = InvalidRequestException.class)
    public void invalid_limits_throws_exception() {
        JFixture fixture = new JFixture();
        fixture.create().inRange(Long.class, 10L, 1L); // min > max
    }
}
