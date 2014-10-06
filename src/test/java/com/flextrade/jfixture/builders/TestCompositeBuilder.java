package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestCompositeBuilder {

    @Mock
    private SpecimenContext mockContext;

    @Mock
    private SpecimenBuilder mockBuilderA;

    @Mock
    private SpecimenBuilder mockBuilderB;

    private CompositeBuilder compositeBuilder;
    private final Object request = new Object();

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        compositeBuilder = new CompositeBuilder(mockBuilderA, mockBuilderB);
    }

    @Test
    public void first_builder_returns_result_is_the_return_value() {
        Object request = new Object();
        Object builderAResult = new Object();
        when(mockBuilderA.create(request, mockContext)).thenReturn(builderAResult);

        Object result = compositeBuilder.create(request, mockContext);

        assertSame(builderAResult, result);
    }

    @Test
    public void first_builder_returns_result_does_not_call_create_on_other_builders() {
        Object builderAResult = new Object();
        when(mockBuilderA.create(request, mockContext)).thenReturn(builderAResult);

        compositeBuilder.create(request, mockContext);

        verify(mockBuilderB, never()).create(request, mockContext);
    }

    @Test
    public void first_builder_returns_no_specimen_returns_result_of_next_builder_with_valid_result() {
        Object builderBResult = new Object();
        when(mockBuilderA.create(request, mockContext)).thenReturn(new NoSpecimen());
        when(mockBuilderB.create(request, mockContext)).thenReturn(builderBResult);

        Object result = compositeBuilder.create(request, mockContext);

        assertSame(builderBResult, result);
    }

    @Test
    public void no_builders_can_satisfy_request_returns_no_specimen() {
        when(mockBuilderA.create(request, mockContext)).thenReturn(new NoSpecimen());
        when(mockBuilderB.create(request, mockContext)).thenReturn(new NoSpecimen());

        Object result = compositeBuilder.create(request, mockContext);

        assertEquals(new NoSpecimen(), result);
    }
}
