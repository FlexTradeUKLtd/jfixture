package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUrlBuilder {

    private UrlBuilder urlBuilder;

    @Before
    public void initialise() {
        this.urlBuilder = new UrlBuilder();
    }

    @Test
    public void non_url_type_request_returns_no_specimen() {
        Object result = this.urlBuilder.create(Integer.class, null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void url_type_request_returns_instance_of_url() {
        Object result = this.urlBuilder.create(URL.class, null);
        assertTrue(result instanceof URL);
    }

    @Test
    public void url_uses_http_protocol() {
        URL url = (URL) this.urlBuilder.create(URL.class, null);
        assertEquals("http", url.getProtocol());
    }

    @Test
    public void url_point_to_localhost() {
        URL url = (URL) this.urlBuilder.create(URL.class, null);
        assertEquals("localhost", url.getHost());
    }
}
