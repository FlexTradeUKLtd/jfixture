package component.annotations;

import com.flextrade.jfixture.annotations.Fixture;
import testtypes.TypeWithProperties;

public class BaseTestClassWithFixture {

    @Fixture
    TypeWithProperties typeWithProperties;
}
