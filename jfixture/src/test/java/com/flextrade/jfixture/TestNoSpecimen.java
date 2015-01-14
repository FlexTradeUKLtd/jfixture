package com.flextrade.jfixture;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestNoSpecimen {

    @Test
    public void Two_instances_are_equal() {
        assertEquals(new NoSpecimen(), new NoSpecimen());
    }
}
