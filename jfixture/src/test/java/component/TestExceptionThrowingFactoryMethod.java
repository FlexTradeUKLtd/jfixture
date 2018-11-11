package component;

import com.flextrade.jfixture.JFixture;
import org.junit.Test;
import testtypes.factorymethods.TypeWithThrowingFactoryMethod;

import static org.junit.Assert.assertNotNull;

public class TestExceptionThrowingFactoryMethod {

    @Test
    public void returns_instance_using_a_factory_method_that_does_not_throw_an_exception() {
        JFixture fixture = new JFixture();
        TypeWithThrowingFactoryMethod type = fixture.create(TypeWithThrowingFactoryMethod.class);

        assertNotNull(type);
    }
}
