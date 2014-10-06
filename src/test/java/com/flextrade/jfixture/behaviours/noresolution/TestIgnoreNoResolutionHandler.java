package com.flextrade.jfixture.behaviours.noresolution;

import com.flextrade.jfixture.NoSpecimen;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestIgnoreNoResolutionHandler {

    @Test
    public void No_specimen_is_returned_when_request_is_handled() {
        IgnoreNoResolutionHandler handler = new IgnoreNoResolutionHandler();
        Object result = handler.handleNoResolution(null, null);
        assertEquals(new NoSpecimen(), result);
    }
}
