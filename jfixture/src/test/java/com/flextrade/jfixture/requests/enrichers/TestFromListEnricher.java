package com.flextrade.jfixture.requests.enrichers;

import com.flextrade.jfixture.annotations.FromListOf;
import com.flextrade.jfixture.requests.ElementFromListRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestFromListEnricher {

    private FromListRequestEnricher enricher;

    @FromListOf(strings = {"a","b"})
    public Object fromFromAnnotationField;

    @Before
    public void initialise() {
        this.enricher = new FromListRequestEnricher();
    }

    @Test
    public void from_list_annotation_returns_from_list_request() throws NoSuchFieldException {
        FromListOf fromListAnnotation = getFromListAnnotation();
        ElementFromListRequest enrichedRequest = (ElementFromListRequest)this.enricher.enrich(String.class, fromListAnnotation);

        assertTrue(enrichedRequest.getList().contains("a"));
        assertTrue(enrichedRequest.getList().contains("b"));
    }

    @Test
    public void non_from_list_annotation_returns_null() throws NoSuchMethodException {
        Before beforeAnnotation = getBeforeAnnotation();
        Object enrichedRequest = this.enricher.enrich(String.class, beforeAnnotation);

        assertNull(enrichedRequest);
    }

    @Test
    public void original_request_not_a_class_returns_null() throws NoSuchFieldException {
        FromListOf fromListAnnotation = getFromListAnnotation();
        Object enrichedRequest = this.enricher.enrich("String", fromListAnnotation);

        assertNull(enrichedRequest);
    }

    private FromListOf getFromListAnnotation() throws NoSuchFieldException {
        return this.getClass().getField("fromFromAnnotationField").getAnnotation(FromListOf.class);
    }

    private Before getBeforeAnnotation() throws NoSuchMethodException {
        return this.getClass().getMethod("initialise").getAnnotation(Before.class);
    }
}
