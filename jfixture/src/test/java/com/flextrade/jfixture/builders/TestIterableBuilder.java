package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestIterableBuilder {

    private IterableBuilder iterableBuilder;

    @Before
    public void initialise() {
        this.iterableBuilder = new IterableBuilder();
    }

    @Test
    public void non_specimen_type_request_returns_no_specimen() {
        Object result = this.iterableBuilder.create("string", null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_iterable_specimen_type_return_no_specimen() {
        Object result = this.iterableBuilder.create(SpecimenType.of(Map.class), null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_interface_iterable_type_returns_no_specimen() {
        Object result = this.iterableBuilder.create(Stack.class, null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void class_type_assignable_to_iterable_returns_instance_of_array_list() {
        Object result = this.iterableBuilder.create(SpecimenType.of(Iterable.class), null);
        assertTrue(result instanceof ArrayList);

        result = this.iterableBuilder.create(SpecimenType.of(List.class), null);
        assertTrue(result instanceof ArrayList);
    }
}
