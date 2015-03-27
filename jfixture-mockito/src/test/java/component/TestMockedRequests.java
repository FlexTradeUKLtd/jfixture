package component;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.mockito.customisation.MockitoCustomisation;
import com.flextrade.jfixture.mockito.testtypes.OrderAbstractClass;
import com.flextrade.jfixture.mockito.testtypes.OrderClass;
import com.flextrade.jfixture.mockito.testtypes.OrderInterface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestMockedRequests {
    @Test
    public void Mockito_customisation_will_return_a_mock_for_an_unsupported_interface_type() {
        JFixture fixture = new JFixture();
        fixture.customise(new MockitoCustomisation());

        OrderInterface order = fixture.create(OrderInterface.class);
        assertTrue(order.getClass().getName().contains("Mockito"));
    }

    @Test
    public void Mockito_customisation_will_return_a_mock_for_an_abstract_class_type() {
        JFixture fixture = new JFixture();
        fixture.customise(new MockitoCustomisation());

        OrderAbstractClass order = fixture.create(OrderAbstractClass.class);
        assertTrue(order.getClass().getName().contains("Mockito"));
    }

    @Test
    public void Mockito_customisation_will_not_return_a_mock_for_a_non_abstract_class_type() {
        JFixture fixture = new JFixture();
        fixture.customise(new MockitoCustomisation());

        OrderClass order = fixture.create(OrderClass.class);
        assertFalse(order.getClass().getName().contains("Mockito"));
    }

    @Test
    public void Mockito_customisation_will_not_return_a_mock_for_a_supported_interface_type() {
        JFixture fixture = new JFixture();
        fixture.customise(new MockitoCustomisation());

        Collection<String> collection = fixture.collections().createCollection(String.class);
        assertTrue(collection instanceof ArrayList);
    }

    @Test
    public void mock_properties_are_stubbed_out_for_interfaces_using_fixture() {
        JFixture fixture = new JFixture();
        fixture.customise(new MockitoCustomisation());

        OrderInterface order = fixture.create(OrderInterface.class);
        assertNotNull(order.getSymbol());
        assertTrue(order.getSize() > 0);
        assertTrue(order.getPrice() > 0.0);
    }

    @Test
    public void mock_properties_are_stubbed_out_for_abstract_classes_using_fixture() {
        JFixture fixture = new JFixture();
        fixture.customise(new MockitoCustomisation());

        OrderAbstractClass order = fixture.create(OrderAbstractClass.class);
        assertNotNull(order.getSymbol());
        assertTrue(order.getSize() > 0);
        assertTrue(order.getPrice() > 0.0);
    }
}
