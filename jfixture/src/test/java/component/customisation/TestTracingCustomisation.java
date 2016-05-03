package component.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.SpecimenSupplier;
import com.flextrade.jfixture.customisation.TracingCustomisation;
import org.junit.Test;
import testtypes.TypeWithProperties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestTracingCustomisation {

    @Test
    public void populates_appendable_instance_with_tracing_data() {
        Appendable stringBuilder = new StringBuilder();
        JFixture fixture = new JFixture();
        fixture.customise(new TracingCustomisation(stringBuilder));

        fixture.create(TypeWithProperties.class);
        String trace = stringBuilder.toString();
        assertFalse(trace.isEmpty());
    }

    @Test
    public void null_specimens_are_traced_correctly_by_confirming_the_instance_is_null() {
        Appendable stringBuilder = new StringBuilder();
        JFixture fixture = new JFixture();
        fixture.customise(new TracingCustomisation(stringBuilder))
               .customise().lazyInstance(String.class, new SpecimenSupplier<String>() {
                    @Override
                    public String create() {
                        return null;
                    }
                });

        fixture.create(String.class);
        String trace = stringBuilder.toString();
        assertTrue(trace.contains("Created: null instance"));
    }
}
