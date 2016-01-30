package com.flextrade.jfixture.runners;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.tracing.MemberOnlyResponseStrategy;
import com.flextrade.jfixture.behaviours.tracing.TracingBehaviour;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class JFixtureJUnitRunner extends Runner {
    private final BlockJUnit4ClassRunner runner;

    public JFixtureJUnitRunner(Class<?> clazz) throws InitializationError {
        this.runner = new BlockJUnit4ClassRunner(clazz) {
            protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
                Statement base = super.withBefores(method, target, statement);
                return new JUnitJFixtureStatement(base, target, new JFixture());
            }
        };
    }

    @Override
    public void run(final RunNotifier notifier) {
        runner.run(notifier);
    }

    @Override
    public Description getDescription() {
        return runner.getDescription();
    }
}