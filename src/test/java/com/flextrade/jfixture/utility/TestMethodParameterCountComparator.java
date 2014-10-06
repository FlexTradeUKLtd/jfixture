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
    private Method few;

    @Before
    public void initialise() throws NoSuchMethodException {
        this.comparator = new MethodParameterCountComparator();

        this.few = TypeWithFactoryMethod.class.getMethod("create", String.class);
        this.many = TypeWithFactoryMethod.class.getMethod("create", String.class, int.class);
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
}

