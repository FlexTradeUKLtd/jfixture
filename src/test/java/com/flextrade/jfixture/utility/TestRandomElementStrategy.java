package com.flextrade.jfixture.utility;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TestRandomElementStrategy {

    private ArrayList<Object> list;
    private RandomElementStrategy strategy;

    @Before
    public void initialise() {
        this.list = new ArrayList<Object>();
        this.strategy = new RandomElementStrategy();
    }

    @Test(expected = IllegalArgumentException.class)
    public void empty_list_throws_exception() {
        this.list.clear();
        this.strategy.get(this.list);
    }

    @Test
    public void list_of_one_element_returns_element() {
        Object element = new Object();
        this.list.add(element);

        Object result = this.strategy.get(this.list);

        assertSame(element, result);
    }

    // Not the best test in the world, but I think it's acceptable
    @Test
    public void list_of_multiple_elements_returns_one() {
        Object element1 = new Object();
        Object element2 = new Object();
        Object element3 = new Object();
        this.list.add(element1);
        this.list.add(element2);
        this.list.add(element3);

        for(int i = 0; i < 100; i++) {
            Object element = this.strategy.get(this.list);
            assertTrue(this.list.contains(element));
        }
    }
}
