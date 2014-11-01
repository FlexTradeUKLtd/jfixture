package com.flextrade.jfixture.utility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestStringUtil {

    @Test
    public void repeats_given_string_n_times() {
        assertEquals("aaa", StringUtil.repeat("a", 3));
        assertEquals("abab", StringUtil.repeat("ab", 2));
    }

    @Test
    public void returns_empty_if_input_string_is_null() {
        assertEquals("", StringUtil.repeat(null, 5));
    }

    @Test
    public void returns_empty_if_repeat_count_is_0() {
        assertEquals("", StringUtil.repeat("a", 0));
    }

    @Test
    public void returns_empty_if_repeat_count_is_less_than_0() {
        assertEquals("", StringUtil.repeat("a", -1));
    }
}
