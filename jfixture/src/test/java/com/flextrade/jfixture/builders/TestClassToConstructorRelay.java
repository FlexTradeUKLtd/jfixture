package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.ConstructorQuery;
import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.specifications.Specification;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import testtypes.constructors.TwoConstructorType;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestClassToConstructorRelay {

    private ClassToConstructorRelay relay;

    @Mock
    private SpecimenContext mockSpecimenContext;

    @Mock
    private ConstructorQuery mockConstructorQuery;

    @Mock
    private Specification mockSpecification;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        when(this.mockSpecification.isSatisfiedBy(any(Object.class))).thenReturn(true);
        when(this.mockConstructorQuery.getConstructorsForClass(TwoConstructorType.class)).thenReturn(Arrays.asList(TwoConstructorType.class.getConstructors()));
        this.relay = new ClassToConstructorRelay(this.mockConstructorQuery, this.mockSpecification);
    }

    @Test
    public void if_request_does_not_satisfy_specification_returns_no_specimen() {
        when(this.mockSpecification.isSatisfiedBy(any(Object.class))).thenReturn(false);
        Object result = this.relay.create(TwoConstructorType.class, mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void if_constructor_query_returns_empty_create_returns_no_specimen() {
        when(this.mockConstructorQuery.getConstructorsForClass(TwoConstructorType.class)).thenReturn(new ArrayList<Constructor<?>>());
        Object result = this.relay.create(TwoConstructorType.class, mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void returns_result_of_context_resolve() {
        Object contextResult = new Object();
        when(mockSpecimenContext.resolve(any(Object.class))).thenReturn(contextResult);
        Object result = this.relay.create(SpecimenType.of(TwoConstructorType.class), mockSpecimenContext);
        assertSame(contextResult, result);
    }
}
