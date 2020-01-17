package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.FactoryMethodQuery;
import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.specifications.Specification;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import testtypes.constructors.TwoConstructorType;
import testtypes.factorymethods.TypeWithFactoryMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestClassToFactoryMethodRelay {

    private ClassToFactoryMethodRelay relay;

    @Mock
    private SpecimenContext mockSpecimenContext;

    @Mock
    private FactoryMethodQuery mockFactoryMethodQuery;

    @Mock
    private Specification mockSpecification;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        when(this.mockSpecification.isSatisfiedBy(any(Object.class))).thenReturn(true);
        List<Method> methods = Arrays.asList(TypeWithFactoryMethod.class.getMethods());
        when(this.mockFactoryMethodQuery.getFactoryMethodsForType(SpecimenType.of(TypeWithFactoryMethod.class))).thenReturn(methods);
        this.relay = new ClassToFactoryMethodRelay(this.mockFactoryMethodQuery, this.mockSpecification);
    }

    @Test
    public void if_request_does_not_satisfy_specification_returns_no_specimen() {
        when(this.mockSpecification.isSatisfiedBy(any(Object.class))).thenReturn(false);
        Object result = this.relay.create(TypeWithFactoryMethod.class, mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void if_factory_method_query_returns_empty_create_returns_no_specimen() {
        when(this.mockFactoryMethodQuery.getFactoryMethodsForType(SpecimenType.of(TypeWithFactoryMethod.class))).thenReturn(Collections.<Method>emptyList());
        Object result = this.relay.create(TwoConstructorType.class, mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void returns_result_of_context_resolve() {
        Object contextResult = new Object();
        when(mockSpecimenContext.resolve(any(Object.class))).thenReturn(contextResult);
        Object result = this.relay.create(SpecimenType.of(TypeWithFactoryMethod.class), mockSpecimenContext);
        assertSame(contextResult, result);
    }
}
