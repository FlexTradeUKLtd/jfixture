package component.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.SpecimenSupplier;
import com.flextrade.jfixture.customisation.InstanceFactoryCustomisation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import testtypes.TypeWithProperties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TestInstanceFactoryCustomisation {

    @Mock
    private SpecimenSupplier<Integer> mockFactoryBuilder;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void calls_factory_builder_create_method_each_time_that_type_is_requested() {
        JFixture fixture = new JFixture();
        fixture.customise(new InstanceFactoryCustomisation<Integer>(Integer.class, mockFactoryBuilder));

        fixture.create(Integer.class);
        fixture.create(Integer.class);

        verify(mockFactoryBuilder, times(2)).create();
    }

    @Test
    public void never_calls_factory_builder_create_method_if_request_type_does_not_match() {
        JFixture fixture = new JFixture();
        fixture.customise(new InstanceFactoryCustomisation<Integer>(Integer.class, mockFactoryBuilder));

        fixture.create(String.class);

        verify(mockFactoryBuilder, times(0)).create();
    }

    @Test
    public void does_not_auto_populate_properties_of_instance() {
        final TypeWithProperties instance = new TypeWithProperties();
        JFixture fixture = new JFixture();
        fixture.customise(new InstanceFactoryCustomisation<TypeWithProperties>(TypeWithProperties.class,
                new SpecimenSupplier<TypeWithProperties>() {
                    @Override
                    public TypeWithProperties create() {
                        return instance;
                    }
                }));

        TypeWithProperties specimen = fixture.create(TypeWithProperties.class);

        assertNull(specimen.getSymbol());
        assertEquals(0, specimen.getSize());
    }
}
