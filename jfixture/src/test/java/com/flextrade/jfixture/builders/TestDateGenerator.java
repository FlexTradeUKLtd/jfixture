package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.utility.TimeProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
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
    public void generates_dates_between_epoch_and_now() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/hh:mm:ss");
        Date nowDate = formatter.parse("01/01/2000/00:00:00");
        Date epochDate = formatter.parse("01/01/1970/00:00:00");

        long nowInMilliseconds = nowDate.getTime();
        long epochInMilliseconds = epochDate.getTime();

        when(mockTimeProvider.getCurrentTimeInMilliseconds()).thenReturn(nowInMilliseconds);

        int runCount = 1000;
        for (int i = 0; i < runCount; i++) {
            Object result = this.dateGenerator.create(Date.class, null);
            long timeInMilliseconds = ((Date) result).getTime();
            assertTrue(timeInMilliseconds >= epochInMilliseconds && timeInMilliseconds <= nowInMilliseconds);
        }
    }
}
