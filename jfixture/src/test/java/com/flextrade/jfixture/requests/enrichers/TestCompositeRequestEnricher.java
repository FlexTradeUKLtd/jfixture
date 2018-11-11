package com.flextrade.jfixture.requests.enrichers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestCompositeRequestEnricher {

    @Mock
    private RequestEnricher mockEnricherA;

    @Mock
    private RequestEnricher mockEnricherB;

    private CompositeRequestEnricher compositeEnricher;

    private final Object request = new Object();

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        compositeEnricher = new CompositeRequestEnricher(this.mockEnricherA, this.mockEnricherB);
    }

    @Test
    public void first_enricher_returns_result_is_the_return_value() {
        Object enricherAResult = new Object();
        when(mockEnricherA.enrich(this.request, null)).thenReturn(enricherAResult);

        Object result = compositeEnricher.enrich(this.request, null);

        assertSame(enricherAResult, result);
    }

    @Test
    public void first_enricher_returns_result_does_not_call_create_on_other_enrichers() {
        Object enricherAResult = new Object();
        when(mockEnricherA.enrich(this.request, null)).thenReturn(enricherAResult);

        compositeEnricher.enrich(this.request, null);

        verify(mockEnricherB, never()).enrich(this.request, null);
    }

    @Test
    public void first_enricher_returns_null_returns_result_of_next_enricher_with_valid_request() {
        Object enricherBResult = new Object();
        when(mockEnricherA.enrich(this.request, null)).thenReturn(null);
        when(mockEnricherB.enrich(this.request, null)).thenReturn(enricherBResult);

        Object result = compositeEnricher.enrich(this.request, null);

        assertSame(enricherBResult, result);
    }

    @Test
    public void no_enrichers_can_satisfy_request_returns_null() {
        when(mockEnricherA.enrich(this.request, null)).thenReturn(null);
        when(mockEnricherB.enrich(this.request, null)).thenReturn(null);

        Object result = compositeEnricher.enrich(this.request, null);

        assertNull(result);
    }
}
