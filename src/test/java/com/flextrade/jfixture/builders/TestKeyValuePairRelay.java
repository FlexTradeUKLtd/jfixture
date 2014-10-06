package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.KeyValueRequest;
import com.flextrade.jfixture.utility.KeyValuePair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestKeyValuePairRelay {

    private KeyValuePairRelay relay;

    @Mock
    private SpecimenContext mockSpecimenContext;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        relay = new KeyValuePairRelay();
    }

    @Test
    public void non_KeyValueRequest_request_type_returns_no_specimen() {
        Object result = this.relay.create(String.class, mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void KeyValueRequest_request_resolves_both_items_and_returns_as_a_KeyValuePair() {
        when(mockSpecimenContext.resolve(String.class)).thenReturn("string");
        when(mockSpecimenContext.resolve(int.class)).thenReturn(1);
        Object result = this.relay.create(new KeyValueRequest(String.class, int.class), mockSpecimenContext);
        assertEquals("string", ((KeyValuePair) result).getKey());
        assertEquals(1, ((KeyValuePair) result).getValue());
    }

    @Test
    public void resolving_first_request_as_no_specimen_returns_no_specimen() {
        when(mockSpecimenContext.resolve(String.class)).thenReturn(new NoSpecimen());
        when(mockSpecimenContext.resolve(int.class)).thenReturn(1);
        Object result = this.relay.create(new KeyValueRequest(String.class, int.class), mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void resolving_second_request_as_no_specimen_returns_no_specimen() {
        when(mockSpecimenContext.resolve(String.class)).thenReturn("string");
        when(mockSpecimenContext.resolve(int.class)).thenReturn(new NoSpecimen());
        Object result = this.relay.create(new KeyValueRequest(String.class, int.class), mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }
}
