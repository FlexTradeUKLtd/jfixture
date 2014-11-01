package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestUriBuilder {

    @Mock
    private SpecimenContext mockSpecimenContext;

    private UriBuilder uriBuilder;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.uriBuilder = new UriBuilder();
    }

    @Test
    public void non_uri_type_request_returns_no_specimen() {
        Object result = this.uriBuilder.create(Integer.class, this.mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void uri_type_request_asks_context_for_url() {
        this.uriBuilder.create(URI.class, this.mockSpecimenContext);
        verify(this.mockSpecimenContext).resolve(URL.class);
    }

    @Test
    public void returns_no_specimen_if_context_returns_non_url_object() {
        when(this.mockSpecimenContext.resolve(URL.class)).thenReturn("string");
        Object specimen = this.uriBuilder.create(URI.class, this.mockSpecimenContext);
        assertEquals(new NoSpecimen(), specimen);
    }

    @Test
    public void returns_uri_if_context_returns_url_object() throws MalformedURLException {
        when(this.mockSpecimenContext.resolve(URL.class)).thenReturn(new URL("http://localhost"));
        Object specimen = this.uriBuilder.create(URI.class, this.mockSpecimenContext);
        assertTrue(specimen instanceof URI);
    }

    @Test
    public void returns_url_as_uri_if_context_returns_url_object() throws MalformedURLException {
        URL expectedUrl = new URL("http://localhost");
        when(this.mockSpecimenContext.resolve(URL.class)).thenReturn(expectedUrl);
        URI uri = (URI) this.uriBuilder.create(URI.class, this.mockSpecimenContext);
        assertEquals(expectedUrl, uri.toURL());
    }
}
