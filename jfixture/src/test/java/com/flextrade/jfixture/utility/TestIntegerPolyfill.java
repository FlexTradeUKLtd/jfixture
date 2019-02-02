package com.flextrade.jfixture.utility;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class TestIntegerPolyfill {

    @Test
    public void negative_integer_less_than_positive() {
        assertThat(IntegerPolyfill.compare(-273, 42), is(lessThan(0)));
        assertThat(IntegerPolyfill.compare(42, -273), is(greaterThan(0)));
    }

    @Test
    public void smaller_positive_integer_less_than_larger_positive_integer() {
        assertThat(IntegerPolyfill.compare(3, 5), is(lessThan(0)));
        assertThat(IntegerPolyfill.compare(5, 3), is(greaterThan(0)));
    }

    @Test
    public void smaller_negative_integer_greater_than_larger_negative_integer() {
        assertThat(IntegerPolyfill.compare(-3, -5), is(greaterThan(0)));
        assertThat(IntegerPolyfill.compare(-5, -3), is(lessThan(0)));
    }

    @Test
    public void integer_equal_to_itself() {
        assertEquals(0, IntegerPolyfill.compare(0, 0));
        assertEquals(0, IntegerPolyfill.compare(42, 42));
        assertEquals(0, IntegerPolyfill.compare(-273, -273));
    }

    @Test
    public void integer_edge_cases_work_as_expected() {
        assertEquals(0, IntegerPolyfill.compare(Integer.MIN_VALUE, Integer.MIN_VALUE));
        assertEquals(0, IntegerPolyfill.compare(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertThat(IntegerPolyfill.compare(Integer.MIN_VALUE, Integer.MAX_VALUE), is(lessThan(0)));
        assertThat(IntegerPolyfill.compare(Integer.MAX_VALUE, Integer.MIN_VALUE), is(greaterThan(0)));
    }
}
