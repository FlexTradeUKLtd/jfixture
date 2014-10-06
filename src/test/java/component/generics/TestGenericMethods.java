package component.generics;

import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.rules.FixtureRule;
import org.junit.Rule;
import org.junit.Test;
import testtypes.generic.TypeWithGenericMethod;

import static junit.framework.TestCase.assertNotNull;

public class TestGenericMethods {

    @Rule
    public FixtureRule fc = FixtureRule.initFixtures();

    @Fixture
    private TypeWithGenericMethod<String> type;

    @Test
    public void populates_type_by_invoking_generic_setters() {
        assertNotNull(type);
        assertNotNull(type.getValue());
    }
}
