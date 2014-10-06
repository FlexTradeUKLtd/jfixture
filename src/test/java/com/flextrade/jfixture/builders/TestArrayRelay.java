package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.MultipleRequest;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class TestArrayRelay {

    private ArrayRelay arrayRelay;

    @Mock
    private SpecimenContext mockSpecimenContext;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        arrayRelay = new ArrayRelay();
    }

    @Test
    public void non_specimen_type_request_returns_no_specimen() {
        Object result = this.arrayRelay.create("string", mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_array_type_request_returns_no_specimen() {
        Object result = this.arrayRelay.create(SpecimenType.of(int.class), mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_list_returned_from_multiple_request_returns_no_specimen() {
        when(mockSpecimenContext.resolve(any(MultipleRequest.class))).thenReturn(String.class);
        Object result = this.arrayRelay.create(int[].class, mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void list_returned_from_multiple_request_is_copied_to_array() {
        Integer[] array = new Integer[]{1, 2, 3};
        List<Integer> list = Arrays.asList(1, 2, 3);
        when(mockSpecimenContext.resolve(any(MultipleRequest.class))).thenReturn(list);
        Integer[] result = (Integer[])this.arrayRelay.create(SpecimenType.of(Integer[].class), mockSpecimenContext);
        assertArrayEquals(array, result);
    }
}
