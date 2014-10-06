package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.SeededRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestSeededStringBuilder {

    private SeededStringBuilder builder;

    @Mock
    private SpecimenContext mockSpecimenContext;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.builder = new SeededStringBuilder();
    }

    @Test
    public void Non_seeded_request_returns_new_no_specimen() {
        Object result = this.builder.create(new Object(), this.mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void Non_string_instance_seed_returns_new_no_specimen() {
        SeededRequest request = new SeededRequest(1, String.class);
        Object result = this.builder.create(request, this.mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void Non_string_class_inner_request_returns_new_no_specimen() {
        SeededRequest request = new SeededRequest("seed", int.class);
        Object result = this.builder.create(request, this.mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void Returns_result_of_context_resolve_for_inner_request_prefixed_with_seed() {
        when(this.mockSpecimenContext.resolve(String.class)).thenReturn("world!");
        SeededRequest request = new SeededRequest("hello, ", String.class);

        Object result = this.builder.create(request, this.mockSpecimenContext);

        assertEquals("hello, world!", result);
    }

    @Test
    public void Returns_no_specimen_if_context_result_is_not_a_string() {
        when(this.mockSpecimenContext.resolve(String.class)).thenReturn(1);
        SeededRequest request = new SeededRequest("seed", String.class);

        Object result = this.builder.create(request, this.mockSpecimenContext);

        assertEquals(new NoSpecimen(), result);
    }
}
