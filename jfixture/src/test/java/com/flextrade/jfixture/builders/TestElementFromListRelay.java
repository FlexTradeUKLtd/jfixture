package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.ElementFromListRequest;
import com.flextrade.jfixture.utility.ElementFromListStrategy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

public class TestElementFromListRelay {

    private ElementFromListRelay relay;

    @Mock
    private ElementFromListStrategy mockElementStrategy;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.relay = new ElementFromListRelay(this.mockElementStrategy);
    }

    @Test
    public void Non_element_from_list_request_returns_no_specimen() {
        Object result = this.relay.create("string", null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test(expected = ObjectCreationException.class)
    public void Empty_list_request_throws_exception() {
        this.relay.create(new ElementFromListRequest(new ArrayList<Object>()), null);
    }

    @Test
    public void Element_from_list_request_returns_result_of_picking_strategy() {
        List<Object> list = new ArrayList<Object>();
        list.add(new Object());
        ElementFromListRequest request = new ElementFromListRequest(list);

        Object specimen = new Object();
        when(this.mockElementStrategy.get(list)).thenReturn(specimen);

        Object result = this.relay.create(request, null);

        assertSame(specimen, result);
    }
}
