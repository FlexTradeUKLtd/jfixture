package com.flextrade.jfixture.requests.enrichers;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestNumberListBuilder {

    private NumberListBuilder builder;

    @Before
    public void initialise() {
        this.builder = new NumberListBuilder();
    }

    @Test
    public void returns_byte_list() {
        Object object = this.builder.getList(new double[] {1.0}, Byte.class).get(0);
        assertTrue(object instanceof Byte);
        assertEquals((byte) 1, object);
    }

    @Test
    public void returns_short_list() {
        Object object = this.builder.getList(new double[] {1.0}, Short.class).get(0);
        assertTrue(object instanceof Short);
        assertEquals((short) 1, object);
    }

    @Test
    public void returns_int_list() {
        Object object = this.builder.getList(new double[] {1.0}, Integer.class).get(0);
        assertTrue(object instanceof Integer);
        assertEquals(1, object);
    }

    @Test
    public void returns_long_list() {
        Object object = this.builder.getList(new double[] {1.0}, Long.class).get(0);
        assertTrue(object instanceof Long);
        assertEquals((long) 1, object);
    }

    @Test
    public void returns_float_list() {
        Object object = this.builder.getList(new double[] {1.0}, Float.class).get(0);
        assertTrue(object instanceof Float);
        assertEquals((float) 1, object);
    }

    @Test
    public void returns_double_list() {
        Object object = this.builder.getList(new double[] {1.0}, Double.class).get(0);
        assertTrue(object instanceof Double);
        assertEquals((double) 1, object);
    }

    @Test
    public void returns_big_int_list() {
        Object object = this.builder.getList(new double[] {1.0}, BigInteger.class).get(0);
        assertTrue(object instanceof BigInteger);
        assertEquals(BigInteger.valueOf(1), object);
    }

    @Test
    public void returns_big_decimal_list() {
        Object object = this.builder.getList(new double[] {1.0}, BigDecimal.class).get(0);
        assertTrue(object instanceof BigDecimal);
        assertEquals(BigDecimal.valueOf(1.0), object);
    }
}
