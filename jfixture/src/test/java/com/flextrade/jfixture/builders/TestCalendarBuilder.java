package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestCalendarBuilder {

    private final Date date = new Date(12345);
    private CalendarBuilder builder;

    @Mock
    private SpecimenContext mockContext;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.builder = new CalendarBuilder();
        when(this.mockContext.resolve(Date.class)).thenReturn(date);
    }

    @Test
    public void non_calendar_type_request_returns_no_specimen() {
        Object result = this.builder.create(String.class, this.mockContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void calls_context_to_resolve_a_date() {
        this.builder.create(Calendar.class, mockContext);
        verify(this.mockContext).resolve(Date.class);
    }

    @Test
    public void returns_gregorian_calendar_with_resolved_date() {
        Object result = this.builder.create(Calendar.class, mockContext);
        assertTrue(result instanceof GregorianCalendar);
        assertEquals(date, ((Calendar) result).getTime());
    }
}