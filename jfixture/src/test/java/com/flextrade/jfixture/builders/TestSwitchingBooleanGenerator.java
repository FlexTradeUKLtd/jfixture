package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSwitchingBooleanGenerator {

    private SwitchingBooleanGenerator booleanGenerator;

    @Before
    public void initialise() {
        booleanGenerator = new SwitchingBooleanGenerator();
    }

    @Test
    public void non_boolean_type_returns_no_specimen() {
        Object result = booleanGenerator.create(Integer.class, null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void boolean_type_returns_alternating_value_initially_true() {
        Object a = booleanGenerator.create(Boolean.class, null);
        Object b = booleanGenerator.create(Boolean.class, null);
        Object c = booleanGenerator.create(Boolean.class, null);
        Object d = booleanGenerator.create(Boolean.class, null);

        assertEquals(true, a);
        assertEquals(false, b);
        assertEquals(true, c);
        assertEquals(false, d);
    }
}
