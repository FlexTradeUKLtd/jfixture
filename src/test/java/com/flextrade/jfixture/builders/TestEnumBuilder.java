package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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
        Object result = this.enumBuilder.create(SpecimenType.of(TestEnum.class), null);
        assertTrue(result instanceof TestEnum);
    }

    @Test
    public void subsequent_requests_return_next_enum_along() {
        Object firstResult = this.enumBuilder.create(SpecimenType.of(TestEnum.class), null);
        Object secondResult = this.enumBuilder.create(SpecimenType.of(TestEnum.class), null);

        assertEquals(TestEnum.ValueA, firstResult);
        assertEquals(TestEnum.ValueB, secondResult);
    }

    @Test
    public void result_loops_around_if_last_enum_generated() {
        Object firstResult = this.enumBuilder.create(SpecimenType.of(TestEnum.class), null);
        this.enumBuilder.create(SpecimenType.of(TestEnum.class), null);
        Object loopedResult = this.enumBuilder.create(SpecimenType.of(TestEnum.class), null);

        assertSame(firstResult, loopedResult);
    }

    private enum TestEnum {
        ValueA,
        ValueB
    }
}
