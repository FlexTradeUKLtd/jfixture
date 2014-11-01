package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.MultipleCount;
import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.MultipleRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestMultipleSpecimenRelay {

    private MultipleSpecimenRelay relay;
    private MultipleCount count;

    @Mock
    private SpecimenContext mockSpecimenContext;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.count = new MultipleCount(3);
        this.relay = new MultipleSpecimenRelay(count);
    }

    @Test
    public void non_multiple_request_type_returns_no_specimen(){
        Object result = this.relay.create(String.class, this.mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void calls_context_with_inner_request_time_n_times() {
        this.relay.create(new MultipleRequest(String.class), this.mockSpecimenContext);
        verify(this.mockSpecimenContext, times(this.count.getCount())).resolve(String.class);
    }

    @Test
    public void returns_no_specimen_if_context_returns_no_specimen() {
        when(mockSpecimenContext.resolve(String.class)).thenReturn(new NoSpecimen());
        Object result = this.relay.create(new MultipleRequest(String.class), this.mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void returns_array_list_of_resolved_types() {
        when(mockSpecimenContext.resolve(String.class)).thenReturn("a").thenReturn("b").thenReturn("c");
        Object result = this.relay.create(new MultipleRequest(String.class), this.mockSpecimenContext);
        assertTrue(result instanceof ArrayList);
        assertEquals(count.getCount(), ((ArrayList) result).size());
        assertEquals("a", ((ArrayList) result).get(0));
        assertEquals("b", ((ArrayList)result).get(1));
        assertEquals("c", ((ArrayList)result).get(2));
    }
}
