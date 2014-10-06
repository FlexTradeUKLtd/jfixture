package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;

import java.net.MalformedURLException;
import java.net.URL;

class UrlBuilder implements SpecimenBuilder {
    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!request.equals(URL.class)) {
            return new NoSpecimen();
        }

        try {
            return new URL("http://localhost");
        } catch (MalformedURLException e) {
            return new NoSpecimen();
        }
    }
}
