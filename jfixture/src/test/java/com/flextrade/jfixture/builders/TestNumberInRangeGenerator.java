package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestNumberInRangeGenerator {

    private NumberInRangeGenerator generator;

    @Before
    public void initialise() {
        this.generator = new NumberInRangeGenerator();
    }

    @Test
    public void non_number_request_returns_no_specimen() {
        assertEquals(new NoSpecimen(), this.generator.create(String.class, null));
        assertEquals(new NoSpecimen(), this.generator.create("string", null));
    }

    @Test
    public void resolves_bytes() {
        Byte result = (Byte)this.generator.create(Byte.class, null);
        assertTrue(result > 0 && result < 127);
    }

    @Test
    public void resolves_shorts() {
        Short result = (Short)this.generator.create(Short.class, null);
        assertTrue(result > 0 && result < 127);
    }

    @Test
    public void resolves_integers() {
        Integer result = (Integer)this.generator.create(Integer.class, null);
        assertTrue(result > 0 && result < 127);
    }

    @Test
    public void resolves_floats() {
        Float result = (Float)this.generator.create(Float.class, null);
        assertTrue(result > 0 && result < 127);
    }

    @Test
    public void resolves_doubles() {
        Double result = (Double)this.generator.create(Double.class, null);
        assertTrue(result > 0 && result < 127);
    }

    @Test
    public void resolves_big_decimals() {
        BigDecimal result = (BigDecimal)this.generator.create(BigDecimal.class, null);
        assertTrue(result.longValue() > 0 && result.longValue() < 127);
    }

    @Test
    public void resolves_big_integers() {
        BigInteger result = (BigInteger)this.generator.create(BigInteger.class, null);
        assertTrue(result.longValue() > 0 && result.longValue() < 127);
    }

    @Test
    public void generates_number_in_the_expected_ranges() {

        for(int i = 0; i < Byte.MAX_VALUE; i++) {
            Long byteLong = (Long)this.generator.create(Long.class, null);
            assertThat(byteLong, is(greaterThan((long)0)));
            assertThat(byteLong, is(lessThanOrEqualTo((long) Byte.MAX_VALUE)));
        }

        for(int i = Byte.MAX_VALUE; i < Short.MAX_VALUE; i++) {
            Long shortLong = (Long)this.generator.create(Long.class, null);
            assertThat(shortLong, is(greaterThan((long)Byte.MAX_VALUE)));
            assertThat(shortLong, is(lessThanOrEqualTo((long)Short.MAX_VALUE)));
        }

        // Can't do it for higher numbers as the test runtime is too high
    }

    @Test
    public void should_create_numbers_in_specified_range() {
        int min = 10;
        int max = 100;
        this.generator = new NumberInRangeGenerator(min, max);
        for (int i = 0; i < max - min; i++) {
            Integer value = (Integer) this.generator.create(Integer.class, null);
            assertTrue(value.toString(), value >= 10);
            assertTrue(value.toString(), value <= 100);
        }
    }

    @Test
    public void should_not_repeat_values_in_range() {
        int min = 10;
        int max = 100;

        HashSet<Integer> seenValues = new HashSet<Integer>();
        this.generator = new NumberInRangeGenerator(min, max);
        for (int i = 0; i < max - min; i++) {
            Integer value = (Integer) this.generator.create(Integer.class, null);
            assertTrue(seenValues.add(value));
        }

        assertEquals(max - min, seenValues.size());
    }
}
