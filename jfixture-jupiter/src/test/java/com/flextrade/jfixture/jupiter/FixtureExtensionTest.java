package com.flextrade.jfixture.jupiter;

import com.flextrade.jfixture.annotations.Fixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(FixtureExtension.class)
class FixtureExtensionTest {
    @Fixture
    private Map<Long, Data<String>> map;

    @Test
    void fixtures_should_be_created_for_maps_with_correct_types() {
        assertNotNull(map);

        assertTrue(map.size() > 0);
        assertTrue(map.keySet().iterator().next().getClass().isAssignableFrom(Long.class));
        assertTrue(map.values().iterator().next().getClass().isAssignableFrom(Data.class));
        assertTrue(map.values().iterator().next().getValue().getClass().isAssignableFrom(String.class));
    }
}
