package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TestEnumBuilder {

    private EnumBuilder enumBuilder;

    @Before
    public void initialise() {
        this.enumBuilder = new EnumBuilder();
    }

    @Test
    public void non_specimen_type_request_returns_no_specimen() {
        Object result = this.enumBuilder.create("string", null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_enum_specimen_type_request_returns_no_specimen() {
        Object result = this.enumBuilder.create(SpecimenType.of(Integer.class), null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void enum_specimen_type_request_returns_instance_of_that_enum() {
        Object result = createEnum();
        assertTrue(result instanceof TestEnum);
    }

    @Test
    public void multiple_requests_within_limit_of_enum_definitions_return_unique_values() {
        Object first = createEnum();
        Object second = createEnum();
        Object third = createEnum();

        assertNotEquals(first, second);
        assertNotEquals(first, third);
        assertNotEquals(second, third);
    }

    @Test
    public void multiple_requests_outside_limit_of_enum_definitions_return_values_in_same_order() {
        Object first = createEnum();
        Object second = createEnum();
        Object third = createEnum();

        assertEquals(first, createEnum());
        assertEquals(second, createEnum());
        assertEquals(third, createEnum());
    }

    private Object createEnum() {
        return this.enumBuilder.create(SpecimenType.of(TestEnum.class), null);
    }

    private enum TestEnum {
        ValueA,
        ValueB,
        ValueC
    }
}
