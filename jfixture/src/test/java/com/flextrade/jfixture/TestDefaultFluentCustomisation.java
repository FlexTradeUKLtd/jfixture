package com.flextrade.jfixture;

import com.flextrade.jfixture.customisation.Customisation;
import com.flextrade.jfixture.customisation.InstanceCustomisation;
import com.flextrade.jfixture.customisation.InstanceFactoryCustomisation;
import com.flextrade.jfixture.customisation.SubTypeCustomisation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestDefaultFluentCustomisation {

    private DefaultFluentCustomisation fluentCustomisation;
    private MultipleCount multipleCount;

    @Mock
    private CustomisationContainer mockCustomisationContainer;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.multipleCount = new MultipleCount();
        this.fluentCustomisation = new DefaultFluentCustomisation(this.mockCustomisationContainer, this.multipleCount);
    }

    @Test
    public void setting_repeat_counts_set_it_on_the_multiple_count_object() {
        this.fluentCustomisation.repeatCount(5);
        assertEquals(5, this.multipleCount.getCount());
    }

    @Test
    public void setting_repeat_counts_returns_itself() {
        assertSame(this.fluentCustomisation, this.fluentCustomisation.repeatCount(0));
    }

    @Test
    public void same_instance_adds_instance_customisation_to_the_container() {
        this.fluentCustomisation.sameInstance(String.class, "string");
        verify(this.mockCustomisationContainer).customise(isA(InstanceCustomisation.class));
    }

    @Test
    public void same_instance_returns_itself() {
        assertSame(this.fluentCustomisation, this.fluentCustomisation.sameInstance(String.class, "string"));
    }

    @Test
    public void lazy_instance_adds_instance_factory_customisation_to_the_container() {
        this.fluentCustomisation.lazyInstance(String.class, null);
        verify(this.mockCustomisationContainer).customise(isA(InstanceFactoryCustomisation.class));
    }

    @Test
    public void lazy_instance_with_functional_interface_adds_instance_factory_customisation_to_the_container() {
        this.fluentCustomisation.lazyInstance(Number.class, mock(SpecimenSupplier.class));
        verify(this.mockCustomisationContainer).customise(isA(InstanceFactoryCustomisation.class));
    }

    @Test
    public void lazy_instance_returns_itself() {
        assertSame(this.fluentCustomisation, this.fluentCustomisation.lazyInstance(String.class, null)) ;
    }

    @Test
    public void lazy_instance__with_functional_interface_returns_itself() {
        assertSame(this.fluentCustomisation, this.fluentCustomisation.lazyInstance(Number.class, mock(SpecimenSupplier.class)));
    }

    @Test
    public void sub_type_adds_sub_type_customisation_to_the_container() {
        this.fluentCustomisation.useSubType(Number.class, int.class);
        verify(this.mockCustomisationContainer).customise(isA(SubTypeCustomisation.class));
    }

    @Test
    public void sub_type_returns_itself() {
        assertSame(this.fluentCustomisation, this.fluentCustomisation.useSubType(Number.class, int.class));
    }

    @Test
    public void adding_customisation_adds_it_to_the_container() {
        Customisation stubCustomisation = mock(Customisation.class);
        this.fluentCustomisation.add(stubCustomisation);
        verify(this.mockCustomisationContainer).customise(stubCustomisation);
    }

    @Test
    public void adding_customisation_returns_itself() {
        assertSame(this.fluentCustomisation, this.fluentCustomisation.add(mock(Customisation.class)));
    }
}
