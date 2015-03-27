package com.flextrade.jfixture.mockito.customisation;

import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;

import com.flextrade.jfixture.mockito.customisation.MockitoAutoPropertySpecification;
import com.flextrade.jfixture.mockito.testtypes.MockitoClass;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class TestMockitoAutoPropertySpecification {

    private MockitoAutoPropertySpecification specification;

    @Before
    public void initialise() {
        specification = new MockitoAutoPropertySpecification();
    }

    @Test
    public void returns_false_if_request_is_not_a_class() {
        assertFalse(this.specification.isSatisfiedBy("string"));
    }

    @Test
    public void returns_false_if_request_is_interface_type() {
        assertFalse(this.specification.isSatisfiedBy(SpecimenType.of(List.class)));
    }

    @Test
    public void returns_false_if_request_class_name_contains_mockito() {
        assertFalse(this.specification.isSatisfiedBy(SpecimenType.of(MockitoClass.class)));
    }
}
