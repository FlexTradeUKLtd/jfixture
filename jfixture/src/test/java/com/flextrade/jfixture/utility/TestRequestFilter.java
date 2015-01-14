package com.flextrade.jfixture.utility;

import com.flextrade.jfixture.requests.MultipleRequest;
import com.flextrade.jfixture.requests.SeededRequest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestRequestFilter {

    @Test
    public void default_filter_does_not_allow_seeded_requests() {
        RequestFilter filter = RequestFilter.onlyDefault();
        boolean allow = filter.allow(new SeededRequest(null, null));
        assertFalse(allow);
    }

    @Test
    public void default_filter_does_not_allow_multiple_requests() {
        RequestFilter filter = RequestFilter.onlyDefault();
        boolean allow = filter.allow(new MultipleRequest(null));
        assertFalse(allow);
    }

    @Test
    public void default_filter_does_allow_type_requests() {
        RequestFilter filter = RequestFilter.onlyDefault();
        boolean allow = filter.allow(SpecimenType.of(String.class));
        assertTrue(allow);
    }

    @Test
    public void all_filter_does_allow_seeded_requests() {
        RequestFilter filter = RequestFilter.all();
        boolean allow = filter.allow(new SeededRequest(null, null));
        assertTrue(allow);
    }

    @Test
    public void all_filter_does_allow_multiple_requests() {
        RequestFilter filter = RequestFilter.all();
        boolean allow = filter.allow(new MultipleRequest(null));
        assertTrue(allow);
    }

    @Test
    public void all_filter_does_allow_type_requests() {
        RequestFilter filter = RequestFilter.all();
        boolean allow = filter.allow(SpecimenType.of(String.class));
        assertTrue(allow);
    }

}