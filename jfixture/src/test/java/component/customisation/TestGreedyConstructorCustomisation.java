package component.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.customisation.GreedyConstructorCustomisation;
import org.junit.Test;
import testtypes.constructors.ThreeConstructorType;
import testtypes.constructors.TwoConstructorType;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestGreedyConstructorCustomisation {

    @Test
    public void creates_type_using_constructor_with_most_parameters() {
        JFixture fixture = new JFixture();
        fixture.customise(new GreedyConstructorCustomisation(TwoConstructorType.class));

        TwoConstructorType type = fixture.create(TwoConstructorType.class);
        assertNotNull(type.getI());
        assertNotNull(type.getJ());
    }

    @Test
    public void only_uses_constructor_with_most_arguments_for_the_specified_type() {
        JFixture fixture = new JFixture();
        fixture.customise(new GreedyConstructorCustomisation(TwoConstructorType.class));

        ThreeConstructorType type = fixture.create(ThreeConstructorType.class);
        assertNotNull(type.getI());
        assertNull(type.getJ());
        assertNull(type.getK());
    }
}
