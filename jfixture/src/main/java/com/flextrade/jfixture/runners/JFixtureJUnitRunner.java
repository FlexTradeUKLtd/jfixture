package com.flextrade.jfixture.runners;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.utility.ParameterUtils;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JFixtureJUnitRunner extends Runner {
    private final BlockJUnit4ClassRunner runner;
    private final JFixture fixture = new JFixture();

    public JFixtureJUnitRunner(Class<?> clazz) throws InitializationError {
        this.runner = new BlockJUnit4ClassRunner(clazz) {
            protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
                return new JUnitJFixtureStatement(statement, target, fixture);
            }

            @Override
            protected void validateTestMethods(List<Throwable> errors) {
                List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(Test.class);
                for (FrameworkMethod eachTestMethod : methods) {
                    eachTestMethod.validatePublicVoid(false, errors);
                }
            }

            @Override
            protected Statement methodInvoker(FrameworkMethod method, Object test) {
                return new InvokeArgsMethod(method, test);
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

    public class InvokeArgsMethod extends Statement {
        private final FrameworkMethod fTestMethod;
        private final Object fTarget;

        public InvokeArgsMethod(FrameworkMethod testMethod, Object target) {
            this.fTestMethod = testMethod;
            this.fTarget = target;
        }

        public void evaluate() throws Throwable {
            Type[] parameterTypes = ParameterUtils.getParameterTypes(this.fTestMethod.getMethod().getGenericParameterTypes(), SpecimenType.of(fTarget.getClass()));

            List<Object> parameters = new ArrayList<Object>();

            for (Type parameterType : parameterTypes) {
                Object parameterInstance = fixture.create(parameterType);
                parameters.add(parameterInstance);
            }

            this.fTestMethod.invokeExplosively(this.fTarget, parameters.toArray(new Object[parameters.size()]));
        }
    }
}