package component.annotations;

import com.flextrade.jfixture.FixtureAnnotations;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestFixtureAnnotationWithHierarchy extends BaseTestClassWithFixture {

    @Test
    public void initialises_auto_fixture_private_fields_in_base_classes_automatically() {
        FixtureAnnotations.initFixtures(this);
        assertNotNull(super.typeWithProperties);
    }

}
