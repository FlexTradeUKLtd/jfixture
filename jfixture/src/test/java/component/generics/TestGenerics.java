package component.generics;

import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.rules.FixtureRule;
import org.junit.Rule;
import org.junit.Test;
import testtypes.generic.TypeWithGenericParameter;
import testtypes.generic.TypeWithGenericParameterWithConstructor;
import testtypes.generic.TypeWithTwoGenericParameters;

import static org.junit.Assert.assertNotNull;

public class TestGenerics {

    @Rule
    public FixtureRule fc = FixtureRule.initFixtures();

    @Fixture
    private TypeWithGenericParameter<String> type;

    @Fixture
    private TypeWithTwoGenericParameters<String, Integer> typeWithTwoGenerics;

    @Fixture
    private TypeWithGenericParameterWithConstructor<String> typeWithConstructor;

    @Test
    public void generic_types_with_default_constructor_can_be_instantiated() {
        assertNotNull(this.type);
    }

    @Test
    public void generic_types_with_multiple_generic_parameters_with_default_constructor_can_be_instantiated() {
        assertNotNull(this.typeWithTwoGenerics);
    }

    @Test
    public void generic_type_with_non_generic_constructor_arguments_can_be_instantiated() {
        assertNotNull(this.typeWithConstructor);
    }
}
