package component;

import com.flextrade.jfixture.JFixture;
import org.junit.Before;
import org.junit.Test;
import testtypes.TypeWithPackagePrivateConstructor;
import testtypes.TypeWithPrivateConstructor;
import testtypes.TypeWithProtectedConstructor;

import static org.junit.Assert.assertNotNull;

public class TestConstructorAccess {

    private JFixture fixture;

    @Before
    public void initialise() {
        fixture = new JFixture();
    }

    @Test
    public void package_private_constructors_are_supported() {
        TypeWithPackagePrivateConstructor instance = this.fixture.create(TypeWithPackagePrivateConstructor.class);
        assertNotNull(instance);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void private_constructors_are_not_supported() {
        TypeWithPrivateConstructor instance = this.fixture.create(TypeWithPrivateConstructor.class);
        assertNotNull(instance);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void protected_constructors_are_not_supported() {
        TypeWithProtectedConstructor instance = this.fixture.create(TypeWithProtectedConstructor.class);
        assertNotNull(instance);
    }
}
