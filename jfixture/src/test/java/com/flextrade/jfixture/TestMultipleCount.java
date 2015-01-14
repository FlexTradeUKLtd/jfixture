package com.flextrade.jfixture;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestMultipleCount {

    private MultipleCount multipleCount;

    @Before
    public void initialise() {
        this.multipleCount = new MultipleCount();
    }

    @Test
    public void initial_count_equals_3() {
        assertEquals(3, this.multipleCount.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setting_count_less_than_0_throws_exception() {
        this.multipleCount.setCount(-1);
    }
}
