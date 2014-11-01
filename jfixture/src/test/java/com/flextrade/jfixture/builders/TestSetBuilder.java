package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSetBuilder {

    private SetBuilder setBuilder;

    @Before
    public void initialise() {
        this.setBuilder = new SetBuilder();
    }

    @Test
    public void non_specimen_type_request_returns_no_specimen() {
        Object result = this.setBuilder.create("string", null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_set_specimen_type_return_no_specimen() {
        Object result = this.setBuilder.create(SpecimenType.of(List.class), null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_interface_set_specimen_type_returns_no_specimen() {
        Object result = this.setBuilder.create(SpecimenType.of(LinkedHashSet.class), null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void class_type_assignable_to_set_returns_instance_of_hash_set() {
        Object result = this.setBuilder.create(SpecimenType.of(Set.class), null);
        assertTrue(result instanceof HashSet);
    }
}
