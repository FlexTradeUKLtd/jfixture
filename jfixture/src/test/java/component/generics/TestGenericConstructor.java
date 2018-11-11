package component.generics;

import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.rules.FixtureRule;
import org.junit.Rule;
import org.junit.Test;
import testtypes.generic.TypeWithGenericConstructor;

import static org.junit.Assert.assertNotNull;

public class TestGenericConstructor {

    @Rule
    public FixtureRule fc = FixtureRule.initFixtures();

    @Fixture
    private TypeWithGenericConstructor<String, Long> type;

    @Test
    public void type_with_generic_constructor_can_be_initialised() {
        assertNotNull(this.type);
        assertNotNull(this.type.gettValue());
        assertNotNull(this.type.getuValue());
    }
}
