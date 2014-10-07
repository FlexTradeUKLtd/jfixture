package com.flextrade.jfixture.rules;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.tracing.MemberOnlyResponseStrategy;
import com.flextrade.jfixture.behaviours.tracing.TracingBehaviour;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
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
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                initialiseAllFixturesInTestClass();
            }

            private void initialiseAllFixturesInTestClass() throws Throwable {
                StringBuilder sb = new StringBuilder();
                try {
                    fixture.behaviours().add(new TracingBehaviour(new MemberOnlyResponseStrategy(), sb));
                    FixtureAnnotations.initFixtures(target, fixture);

                    base.evaluate();
                }  catch (Throwable t) {
                    failed(t, sb);
                    throw t;
                }
            }

            public void failed(Throwable e, StringBuilder sb) {
                if (e instanceof ObjectCreationException) return;

                System.err.println(sb.toString());
            }
        };
    }

    public JFixture getFixture() {
        return this.fixture;
    }
}