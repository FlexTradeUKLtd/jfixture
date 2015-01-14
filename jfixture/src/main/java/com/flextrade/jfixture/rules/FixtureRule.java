package com.flextrade.jfixture.rules;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.runners.JUnitJFixtureStatement;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class FixtureRule implements MethodRule {

    private final JFixture fixture;

    private FixtureRule() {
        this(new JFixture());
    }

    private FixtureRule(JFixture fixture) {
        this.fixture = fixture;
    }

    public static FixtureRule initFixtures() {
        return new FixtureRule();
    }

    public static FixtureRule initFixtures(JFixture fixtureEngine) {
        return new FixtureRule(fixtureEngine);
    }

    @Override
    public Statement apply(final Statement base, final FrameworkMethod method, final Object target) {
      return new JUnitJFixtureStatement(base, target, fixture);
    }

    public JFixture getFixture() {
        return this.fixture;
    }
}