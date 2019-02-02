package com.flextrade.jfixture.utility;

import com.flextrade.jfixture.utility.comparators.MethodParameterCountComparator;
import org.junit.Before;
import org.junit.Test;
import testtypes.factorymethods.TypeWithFactoryMethod;

import java.lang.reflect.Method;

import static org.junit.Assert.assertTrue;

public class TestMethodParameterCountComparator {

    private MethodParameterCountComparator comparator;
    private Method many;
    private Method manyReverse;
    private Method few;

    @Before
    public void initialise() throws NoSuchMethodException {
        this.comparator = new MethodParameterCountComparator();

        this.few = TypeWithFactoryMethod.class.getMethod("create", String.class);
        this.many = TypeWithFactoryMethod.class.getMethod("create", String.class, int.class);
        this.manyReverse = TypeWithFactoryMethod.class.getMethod("create", int.class, String.class);
    }

    @Test
    public void returns_less_than_0_if_first_method_has_fewer_parameters_than_second() {
        int result = this.comparator.compare(few, many);
        assertTrue(result < 0);
    }

    @Test
    public void returns_greater_than_0_if_first_method_has_more_parameters_than_second() {
        int result = this.comparator.compare(many, few);
        assertTrue(result > 0);
    }

    @Test
    public void returns_0_if_methods_have_same_number_of_parameters() {
        int result = this.comparator.compare(few, few);
        assertTrue(result == 0);
    }

    @Test
    public void returns_non_0_if_methods_have_same_number_of_parameters_but_overloaded() {
        int result = this.comparator.compare(many, manyReverse);
        assertTrue(result != 0);
    }

    @Test
    public void returns_less_than_0_if_methods_have_same_number_of_parameters_but_lexicographically_different() {
        int result = this.comparator.compare(manyReverse, many);
        assertTrue(result < 0);
    }

    @Test
    public void returns_greater_than_0_if_methods_have_same_number_of_parameters_but_lexicographically_different() {
        int result = this.comparator.compare(many, manyReverse);
        assertTrue(result > 0);
    }
}
