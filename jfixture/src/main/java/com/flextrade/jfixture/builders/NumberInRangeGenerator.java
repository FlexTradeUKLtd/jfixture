package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class NumberInRangeGenerator implements SpecimenBuilder {

    private final long[] limits;
    private final HashSet<Long> numbers;
    private long lower;
    private long upper;
    private long count;
    private final Random random;

    public NumberInRangeGenerator() {
        this(1, Byte.MAX_VALUE, Short.MAX_VALUE, Integer.MAX_VALUE);
    }

    public NumberInRangeGenerator(long... limits) {
        this.limits = limits;
        this.numbers = new HashSet<Long>();
        this.random = new Random();
        this.createRange();
    }

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (request.equals(Byte.class)) {
            return (byte) getNextRandom();
        } else if (request.equals(Short.class)) {
            return (short) getNextRandom();
        } else if (request.equals(Integer.class)) {
            return (int) getNextRandom();
        } else if (request.equals(Long.class)) {
            return getNextRandom();
        } else if (request.equals(Float.class)) {
            return (float) getNextRandom();
        } else if (request.equals(Double.class)) {
            return (double) getNextRandom();
        } else if (request.equals(BigDecimal.class)) {
            return BigDecimal.valueOf(getNextRandom());
        } else if (request.equals(BigInteger.class)) {
            return BigInteger.valueOf(getNextRandom());
        }

        return new NoSpecimen();
    }

    private long getNextRandom() {
        this.evaluateRange();

        long result;
        do {
            result = (long)(this.random.nextDouble() * (this.upper - this.lower)) + this.lower;
        }
        while (this.numbers.contains(result));

        this.numbers.add(result);
        return result;
    }

    private void evaluateRange() {
        if (this.count == (this.upper - this.lower)) {
            this.count = 0;
            this.createRange();
        }

        this.count++;
    }

    private void createRange() {
        List<Long> remaining = getRemainingValues();
        if (remaining.size() > 0 && this.numbers.size() > 0) {
            this.lower = this.upper;
            this.upper = Collections.min(remaining) + 1;
        } else {
            this.lower = limits[0];
            this.upper = limits[1];
        }

        this.numbers.clear();
    }

    private List<Long> getRemainingValues() {
        List<Long> remaining = new ArrayList<Long>();
        for (long l : this.limits) {
            if (l > this.upper - 1) remaining.add(l);
        }

        return remaining;
    }
}
