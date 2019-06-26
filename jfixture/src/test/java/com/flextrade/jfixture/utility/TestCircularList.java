package com.flextrade.jfixture.utility;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TestCircularList {

    @Test(expected = IllegalArgumentException.class)
    public void null_source_list_throws_exception() {
        new CircularList<String>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void empty_source_list_throws_exception() {
        new CircularList<String>(new ArrayList<String>());
    }

    @Test
    public void when_next_called_with_single_item_in_source_always_returns_same_item() {
        String item = UUID.randomUUID().toString();
        List<String> source = new ArrayList<String>();
        source.add(item);
        CircularList<String> list = new CircularList<String>(source);

        assertEquals(item, list.next());
        assertEquals(item, list.next());
        assertEquals(item, list.next());
    }

    @Test
    public void when_next_called_with_two_items_in_source_items_are_returned_in_order() {
        String item1 = UUID.randomUUID().toString();
        String item2 = UUID.randomUUID().toString();
        List<String> source = new ArrayList<String>();
        source.add(item1);
        source.add(item2);
        CircularList<String> list = new CircularList<String>(source);

        assertEquals(item1, list.next());
        assertEquals(item2, list.next());
    }

    @Test
    public void when_next_called_three_times_with_two_items_in_source_list_is_reset() {
        String item1 = UUID.randomUUID().toString();
        String item2 = UUID.randomUUID().toString();
        List<String> source = new ArrayList<String>();
        source.add(item1);
        source.add(item2);
        CircularList<String> list = new CircularList<String>(source);

        assertEquals(item1, list.next());
        assertEquals(item2, list.next());
        assertEquals(item1, list.next());
    }
}
