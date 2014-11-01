package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.FactoryMethodRequest;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import testtypes.factorymethods.TypeWithFactoryMethod;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class TestFactoryMethodRelay {

    private FactoryMethodRelay relay;

    @Mock
    private SpecimenContext mockSpecimenContext;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        relay = new FactoryMethodRelay();
    }

    @Test
    public void non_factory_method_request_returns_no_specimen() {
        Object specimen = this.relay.create(String.class, mockSpecimenContext);
        assertEquals(new NoSpecimen(), specimen);
    }

    @Test(expected = ObjectCreationException.class)
    public void throws_exception_if_method_parameters_are_invalid() throws NoSuchMethodException {
        when(mockSpecimenContext.resolve(SpecimenType.of(String.class))).thenReturn(1);
        when(mockSpecimenContext.resolve(SpecimenType.of(int.class))).thenReturn("string");
        this.relay.create(getRequest(), mockSpecimenContext);
    }

    @Test
    public void method_is_invoked_with_parameters_from_specimen_context() throws NoSuchMethodException {
        when(mockSpecimenContext.resolve(SpecimenType.of(String.class))).thenReturn("string");
        when(mockSpecimenContext.resolve(SpecimenType.of(int.class))).thenReturn(1);
        Object specimen = this.relay.create(getRequest(), mockSpecimenContext);
        assertTrue(specimen instanceof TypeWithFactoryMethod);
        assertEquals("string", ((TypeWithFactoryMethod) specimen).getSymbol());
        assertEquals(1, ((TypeWithFactoryMethod) specimen).getSize());
    }

    private static Method getFactoryMethod() throws NoSuchMethodException {
        return TypeWithFactoryMethod.class.getDeclaredMethod("create", String.class, int.class);
    }

    private static FactoryMethodRequest getRequest() throws NoSuchMethodException {
        return new FactoryMethodRequest(getFactoryMethod(), SpecimenType.of(TypeWithFactoryMethod.class));
    }
}
