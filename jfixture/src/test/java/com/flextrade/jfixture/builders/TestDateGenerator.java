package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.utility.TimeProvider;
import org.hamcrest.number.OrderingComparison;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class TestDateGenerator {

    private DateGenerator dateGenerator;

    @Mock
    private TimeProvider mockTimeProvider;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.dateGenerator = new DateGenerator(this.mockTimeProvider);
    }

    @Test
    public void non_date_type_request_returns_no_specimen() {
        Object result = this.dateGenerator.create(Integer.class, null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void date_type_request_returns_date_instance() {
        Object result = this.dateGenerator.create(Date.class, null);
        assertTrue(result instanceof Date);
    }

    @Test
    public void generates_dates_at_current_date_within_plus_or_minus_two_years() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date nowDate = formatter.parse("2000/01/01 00:00:00");
        Date plusTwoYears = formatter.parse("2002/01/01 00:00:00");
        Date minusTwoYears = formatter.parse("1998/01/01 00:00:00");

        when(mockTimeProvider.getCurrentTimeInMilliseconds()).thenReturn(nowDate.getTime());

        int runCount = 1000;
        for (int i = 0; i < runCount; i++) {
            Object result = this.dateGenerator.create(Date.class, null);
            Date date = (Date)result;

            assertThat(date, greaterThanOrEqualTo(minusTwoYears));
            assertThat(date, lessThanOrEqualTo(plusTwoYears));
        }
    }
}
