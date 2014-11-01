package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMapBuilder {

    private MapBuilder mapBuilder;

    @Before
    public void initialise() {
        this.mapBuilder = new MapBuilder();
    }

    @Test
    public void non_specimen_type_request_returns_no_specimen() {
        Object result = this.mapBuilder.create("string", null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_map_specimen_type_return_no_specimen() {
        Object result = this.mapBuilder.create(SpecimenType.of(List.class), null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_interface_map_type_returns_no_specimen() {
        Object result = this.mapBuilder.create(SpecimenType.of(LinkedHashMap.class), null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void class_type_assignable_to_map_returns_instance_of_hash_map() {
        Object result = this.mapBuilder.create(SpecimenType.of(Map.class), null);
        assertTrue(result instanceof HashMap);

        result = this.mapBuilder.create(SpecimenType.of(SortedMap.class), null);
        assertTrue(result instanceof HashMap);
    }
}
