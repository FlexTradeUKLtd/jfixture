package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

class UriBuilder implements SpecimenBuilder {
    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!request.equals(URI.class)) {
            return new NoSpecimen();
        }

        Object specimen = context.resolve(URL.class);
        if (!(specimen instanceof URL)) {
            return new NoSpecimen();
        }

        URL url = (URL) specimen;
        try {
            return url.toURI();
        } catch (URISyntaxException e) {
            return new NoSpecimen();
        }
    }
}