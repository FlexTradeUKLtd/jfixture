package component;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Test;
import testtypes.factorymethods.AbstractTypeWithFactory;
import testtypes.factorymethods.ConcreteType;
import testtypes.generic.TypeWithGenericFactoryMethod;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestFactoryMethod {

    @Test
    public void factory_method_invoked_even_for_abstract_type() {
        JFixture fixture = new JFixture();

        AbstractTypeWithFactory type = fixture.create(AbstractTypeWithFactory.class);

        assertTrue(type instanceof ConcreteType);
    }
    @Test
    public void factory_method_invoked_for_generic_types() {
        JFixture fixture = new JFixture();

        TypeWithGenericFactoryMethod<String> type = fixture.create(new SpecimenType<TypeWithGenericFactoryMethod<String>>() {});

        assertNotNull(type);
        assertNotNull(type.getValue());
    }
}
