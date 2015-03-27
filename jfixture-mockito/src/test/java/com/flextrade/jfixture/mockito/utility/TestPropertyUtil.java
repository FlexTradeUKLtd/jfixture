package com.flextrade.jfixture.mockito.utility;

import org.junit.Test;

import com.flextrade.jfixture.mockito.testtypes.PropertyUtilTestClass;

import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;

public class TestPropertyUtil {

    @Test
    public void is_getter_returns_false_if_method_does_not_start_with_get() throws NoSuchMethodException {
        Method method = PropertyUtilTestClass.class.getMethod("theSize");
        assertFalse(PropertyUtil.isMethodAGetterProperty(method));
    }

    @Test
    public void is_getter_returns_false_if_method_has_parameters() throws NoSuchMethodException {
        Method method = PropertyUtilTestClass.class.getMethod("getId", int.class);
        assertFalse(PropertyUtil.isMethodAGetterProperty(method));
    }

    @Test
    public void is_getter_returns_false_if_no_matching_setter_exists() throws NoSuchMethodException {
        Method method = PropertyUtilTestClass.class.getMethod("getSymbol");
        assertFalse(PropertyUtil.isMethodAGetterProperty(method));
    }
}
