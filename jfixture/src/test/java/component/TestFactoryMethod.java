package component;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Test;
import testtypes.factorymethods.AbstractTypeWithFactory;
import testtypes.factorymethods.ConcreteType;
import testtypes.factorymethods.GenericTypeWithCopyFactoryMethod;
import testtypes.factorymethods.TypeWithCopyFactoryMethod;
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

    @Test 
    public void factory_copy_methods_are_not_called() {
        JFixture fixture = new JFixture();

        TypeWithCopyFactoryMethod type = fixture.create(TypeWithCopyFactoryMethod.class);
        
        assertNotNull(type);
    }

    @Test
    public void factory_copy_methods_are_not_called_on_generic_classes() {
        JFixture fixture = new JFixture();

        GenericTypeWithCopyFactoryMethod<String> type = fixture.create(new SpecimenType<GenericTypeWithCopyFactoryMethod<String>>() {});

        assertNotNull(type);
    }
}
