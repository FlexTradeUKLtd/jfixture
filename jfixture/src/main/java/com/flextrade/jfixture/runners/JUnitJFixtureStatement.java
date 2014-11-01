package com.flextrade.jfixture.runners;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.tracing.MemberOnlyResponseStrategy;
import com.flextrade.jfixture.behaviours.tracing.TracingBehaviour;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import org.junit.runners.model.Statement;

public class JUnitJFixtureStatement extends Statement {

    private final Statement base;
    private final Object target;
    private final JFixture fixture;

    public JUnitJFixtureStatement(Statement base, Object target, JFixture fixture) {
        this.base = base;
        this.target = target;
        this.fixture = fixture;
    }

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
        } catch (Throwable t) {
            failed(t, sb);
            throw t;
        }
    }

    private void failed(Throwable e, StringBuilder sb) {
        if (e instanceof ObjectCreationException) return;

        System.err.println(sb.toString());
    }
}