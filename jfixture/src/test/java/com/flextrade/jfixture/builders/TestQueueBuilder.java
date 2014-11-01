package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestQueueBuilder {

    private QueueBuilder queueBuilder;

    @Before
    public void initialise() {
        this.queueBuilder = new QueueBuilder();
    }

    @Test
    public void non_specimen_type_request_returns_no_specimen() {
        Object result = this.queueBuilder.create("string", null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_queue_specimen_type_return_no_specimen() {
        Object result = this.queueBuilder.create(SpecimenType.of(List.class), null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void non_interface_queue_type_returns_no_specimen() {
        Object result = this.queueBuilder.create(SpecimenType.of(LinkedList.class), null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void class_type_assignable_to_queue_returns_instance_of_array_deque() {
        Object result = this.queueBuilder.create(SpecimenType.of(Queue.class), null);
        assertTrue(result instanceof ArrayDeque);
    }
}
