package com.flextrade.jfixture.requests.enrichers;

import com.flextrade.jfixture.annotations.Range;
import com.flextrade.jfixture.requests.RangeRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class TestRangeRequestEnricher {

    private RangeRequestEnricher enricher;

    private final Object originalRequest = new Object();

    @Range(min = 10, max = 100)
    public Object rangeAnnotatedField;

    @Before
    public void initialise() {
        this.enricher = new RangeRequestEnricher();
    }

    @Test
    public void range_annotation_returns_range_request() throws NoSuchFieldException {
        Range rangeAnnotation = getRangeAnnotation();
        RangeRequest enrichedRequest = (RangeRequest)this.enricher.enrich(this.originalRequest, rangeAnnotation);

        assertSame(this.originalRequest, enrichedRequest.getRequest());
        assertEquals(10L, enrichedRequest.getMin());
        assertEquals(100L, enrichedRequest.getMax());
    }

    @Test
    public void non_range_annotation_returns_null() throws NoSuchMethodException {
        Before beforeAnnotation = getBeforeAnnotation();
        Object enrichedRequest = this.enricher.enrich(this.originalRequest, beforeAnnotation);

        assertNull(enrichedRequest);
    }

    private Range getRangeAnnotation() throws NoSuchFieldException {
        return this.getClass().getField("rangeAnnotatedField").getAnnotation(Range.class);
    }

    private Before getBeforeAnnotation() throws NoSuchMethodException {
        return this.getClass().getMethod("initialise").getAnnotation(Before.class);
    }
}