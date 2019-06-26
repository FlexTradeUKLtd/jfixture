package component.runners;

import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.runners.JFixtureJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(JFixtureJUnitRunner.class)
public class TestJFixtureJUnitRunner {

    @Fixture String value;
    private Object testSubject;

    @Before
    public void setUp() {
        testSubject = new Object();
    }

    @Test
    public void runner_instantiates_fields_marked_with_fixture_annotation() {
        assertNotNull(value);
    }

    @Test
    public void runner_calls_setup_method_before_running_tests() {
        assertNotNull(testSubject);
    }
}
