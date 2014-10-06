package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.SeededRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

public class TestSeedIgnoringBuilder {

    private SeedIgnoringRelay builder;

    @Mock
    private SpecimenContext mockSpecimenContext;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.builder = new SeedIgnoringRelay();
    }

    @Test
    public void Non_seeded_request_returns_new_no_specimen() {
        Object result = this.builder.create(new Object(), this.mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void Returns_context_resolve_given_inner_request_object() {
        Object innerRequest = new Object();
        Object contextResult = new Object();
        when(this.mockSpecimenContext.resolve(innerRequest)).thenReturn(contextResult);

        Object result = this.builder.create(new SeededRequest(null, innerRequest), this.mockSpecimenContext);
        assertSame(contextResult, result);
    }

    @Test
    public void No_specimen_result_from_context_returns_new_no_specimen() {
        Object innerRequest = new Object();
        when(this.mockSpecimenContext.resolve(innerRequest)).thenReturn(new NoSpecimen());

        Object result = this.builder.create(new SeededRequest(null, innerRequest), this.mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }
}
