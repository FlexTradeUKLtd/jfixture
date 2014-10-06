package component;

import com.flextrade.jfixture.JFixture;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import testtypes.TypeIsAbstract;
import testtypes.TypeIsInterface;
import testtypes.TypeWithPrivateConstructor;
import testtypes.TypeWithProtectedConstructor;

public class TestErrorMessages {

    private JFixture fixture;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void initialise() {
        this.fixture = new JFixture();
    }

    @Test
    public void error_message_for_interface_requests_is_helpful() {
        exception.expect(UnsupportedOperationException.class);
        exception.expectMessage("TypeIsInterface is interface type. Ensure this interface type can be resolved to a concrete type");

        this.fixture.create(TypeIsInterface.class);
    }

    @Test
    public void error_message_for_abstract_class_requests_is_helpful() {
        exception.expect(UnsupportedOperationException.class);
        exception.expectMessage("TypeIsAbstract is abstract type. Ensure this type can be resolved to a concrete type");

        this.fixture.create(TypeIsAbstract.class);
    }

    @Test
    public void error_message_for_class_with_private_constructor_requests_is_helpful() {
        exception.expect(UnsupportedOperationException.class);
        exception.expectMessage("TypeWithPrivateConstructor does not contain a public constructor");

        this.fixture.create(TypeWithPrivateConstructor.class);
    }

    @Test
    public void error_message_for_class_with_protected_constructor_requests_is_helpful() {
        exception.expect(UnsupportedOperationException.class);
        exception.expectMessage("TypeWithProtectedConstructor does not contain a public constructor");

        this.fixture.create(TypeWithProtectedConstructor.class);
    }
}
