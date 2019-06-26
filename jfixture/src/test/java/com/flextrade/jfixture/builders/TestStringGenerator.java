package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestStringGenerator {

    private StringGenerator stringGenerator;

    @Before
    public void initialise() {
        this.stringGenerator = new StringGenerator();
    }

    @Test
    public void non_string_type_request_returns_no_specimen() {
        Object result = this.stringGenerator.create(Integer.class, null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void string_type_request_returns_string_as_uuid() {
        Object result = this.stringGenerator.create(String.class, null);
        assertNotNull(UUID.fromString((String) result));
    }
}
