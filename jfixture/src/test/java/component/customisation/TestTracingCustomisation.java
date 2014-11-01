package component.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.customisation.TracingCustomisation;
import org.junit.Test;
import testtypes.TypeWithProperties;

import static org.junit.Assert.assertFalse;

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
}
