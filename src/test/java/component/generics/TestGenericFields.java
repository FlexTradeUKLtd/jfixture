package component.generics;

import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.rules.FixtureRule;
import org.junit.Rule;
import org.junit.Test;
import testtypes.generic.TypeWithGenericField;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class TestGenericFields {

    @Rule
    public FixtureRule fc = FixtureRule.initFixtures();

    @Fixture
    private TypeWithGenericField<String> type;

    @SuppressWarnings("ConstantConditions")
    @Test
    public void populates_type_by_invoking_generic_fields() {
        assertNotNull(type);
        assertNotNull(type.value);
        assertTrue("Value field should be a String", type.value instanceof String);
    }
}
