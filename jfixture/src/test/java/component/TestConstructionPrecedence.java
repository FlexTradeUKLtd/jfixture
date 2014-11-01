package component;

import com.flextrade.jfixture.JFixture;
import org.junit.Test;
import testtypes.TypeWithConstructorAndFactoryMethod;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestConstructionPrecedence {

    @Test
    public void constructor_should_be_used_over_factory_method() {
        JFixture fixture = new JFixture();
        TypeWithConstructorAndFactoryMethod type = fixture.create(TypeWithConstructorAndFactoryMethod.class);

        assertNotNull(type.getCtorArg());
        assertNull(type.getMethodArg());
    }
}
