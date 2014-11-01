package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUuidGenerator {

    private UuidGenerator uuidGenerator;

    @Before
    public void initialise() {
        this.uuidGenerator = new UuidGenerator();
    }

    @Test
    public void non_uuid_type_request_returns_no_specimen() {
        Object result = this.uuidGenerator.create(Integer.class, null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void uuid_type_request_returns_uuid_instance() {
        Object result = this.uuidGenerator.create(UUID.class, null);
        assertTrue(result instanceof UUID);
    }
}
