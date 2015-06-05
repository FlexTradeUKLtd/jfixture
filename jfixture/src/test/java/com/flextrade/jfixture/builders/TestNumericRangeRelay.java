package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.InvalidRequestException;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.RangeRequest;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestNumericRangeRelay {

    @Mock
    private SpecimenContext mockContext;

    private NumericRangeRelay numericRangeRelay;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.numericRangeRelay = new NumericRangeRelay();
    }

    @Test
    public void non_range_request_type_returns_no_specimen() {
        Object result = this.numericRangeRelay.create(Integer.class, this.mockContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_numeric_inner_request_returns_no_specimen() {
        RangeRequest request = new RangeRequest<Long>(String.class, 1L, 10L);
        Object result = this.numericRangeRelay.create(request, this.mockContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test(expected = InvalidRequestException.class)
    public void throws_exception_if_min_is_greater_than_max() {
        RangeRequest request = new RangeRequest<Long>(Long.class, 10L, 1L);
        this.numericRangeRelay.create(request, this.mockContext);
    }

    @Test(expected = InvalidRequestException.class)
    public void throws_exception_if_min_is_equal_to_than_max() {
        RangeRequest request = new RangeRequest<Long>(Long.class, 10L, 10L);
        this.numericRangeRelay.create(request, this.mockContext);
    }

    @Test
    public void returns_a_number_in_the_specified_range() {
        RangeRequest request = new RangeRequest<Long>(SpecimenType.of(Long.class), 200L, 300L);
        Long result = (Long)this.numericRangeRelay.create(request, this.mockContext);
        assertTrue(result >= 200 && result < 300);
    }
}
