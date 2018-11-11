package component;

import com.flextrade.jfixture.JFixture;
import org.junit.Test;
import testtypes.constructors.TypeWithThrowingConstructor;

import static org.junit.Assert.assertNotNull;

public class TestExceptionThrowingConstructor {

    @Test
    public void returns_instance_using_a_constructor_that_does_not_throw_an_exception() {
        JFixture fixture = new JFixture();
        TypeWithThrowingConstructor type = fixture.create(TypeWithThrowingConstructor.class);

        assertNotNull(type);
    }
}
