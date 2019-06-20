package com.flextrade.jfixture.jupiter;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class FixtureExtension implements TestInstancePostProcessor {
    private final JFixture fixture = new JFixture();

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        FixtureAnnotations.initFixtures(testInstance, fixture);
    }
}
