package com.flextrade.jfixture.jupiter;

import com.flextrade.jfixture.annotations.Fixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class FixtureResolverTest {

    @Test
    @ExtendWith(FixtureResolver.class)
    public void fixtures_should_create_String_correctly(@Fixture String data) {
        Assertions.assertNotNull(data);
    }

    @Test
    @ExtendWith(FixtureResolver.class)
    public void fixtures_should_create_int_correctly(@Fixture int data) {
        Assertions.assertNotEquals(0, data);
    }

    @Test
    @ExtendWith(FixtureResolver.class)
    public void fixtures_should_create_class_with_type_correctly(
            @Fixture FixtureResolverTestData data) {

        Assertions.assertNotNull(data.getStringField());
        Assertions.assertNotEquals(0, data.getIntField());
        Assertions.assertFalse(data.getListField().isEmpty());
        Assertions.assertFalse(data.getMapField().isEmpty());
    }

}
