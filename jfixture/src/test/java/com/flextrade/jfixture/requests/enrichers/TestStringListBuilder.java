package com.flextrade.jfixture.requests.enrichers;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestStringListBuilder {

    private StringListBuilder builder;

    @Before
    public void initialise() {
        this.builder = new StringListBuilder();
    }

    @Test
    public void returns_list_of_strings() {
        String[] array = {"a", "b", "c"};
        List<Object> objects = this.builder.getList(array);

        assertEquals(3, objects.size());
        assertEquals("a", objects.get(0));
        assertEquals("b", objects.get(1));
        assertEquals("c", objects.get(2));
    }
}
