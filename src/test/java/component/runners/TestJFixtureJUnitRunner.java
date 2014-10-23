package component.runners;

import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.runners.JFixtureJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(JFixtureJUnitRunner.class)
public class TestJFixtureJUnitRunner {

    @Fixture String value;

    @Test
    public void runner_instantiates_fields_marked_with_fixture_annotation() {
        assertNotNull(value);
    }
}
