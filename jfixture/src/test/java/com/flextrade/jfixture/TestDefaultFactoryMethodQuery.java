package com.flextrade.jfixture;

import com.flextrade.jfixture.utility.SpecimenType;
import com.flextrade.jfixture.utility.comparators.InverseComparator;
import com.flextrade.jfixture.utility.comparators.MethodParameterCountComparator;
import org.junit.Before;
import org.junit.Test;
import testtypes.factorymethods.AbstractTypeWithFactory;
import testtypes.factorymethods.TypeWithFactoryMethod;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestDefaultFactoryMethodQuery {

    private DefaultFactoryMethodQuery query;
    private Method expectedMethodOneParameter;
    private Method expectedMethodTwoParameters;

    @Before
    public void initialise() throws Exception {
        expectedMethodOneParameter = TypeWithFactoryMethod.class.getMethod("create", String.class);
        expectedMethodTwoParameters = TypeWithFactoryMethod.class.getMethod("create", String.class, int.class);
    }

    @Test
    public void returns_all_factory_methods_for_a_given_class() throws NoSuchMethodException {
        // Factory methods are public, static methods with return type assignable to declared type
        this.query = new DefaultFactoryMethodQuery();

        List<Method> factoryMethods = this.query.getFactoryMethodsForType(SpecimenType.of(TypeWithFactoryMethod.class));

        assertEquals(2, factoryMethods.size());
        assertTrue(factoryMethods.contains(expectedMethodOneParameter));
        assertTrue(factoryMethods.contains(expectedMethodTwoParameters));
    }

    @Test
    public void sorts_methods_using_given_comparator_ascending_parameter_count() {
        this.query = new DefaultFactoryMethodQuery(new MethodParameterCountComparator());

        List<Method> factoryMethods = this.query.getFactoryMethodsForType(SpecimenType.of(TypeWithFactoryMethod.class));

        assertEquals(expectedMethodOneParameter, factoryMethods.get(0));
        assertEquals(expectedMethodTwoParameters, factoryMethods.get(1));
    }

    @Test
    public void sorts_methods_using_given_comparator_descending_parameter_count() {
        this.query = new DefaultFactoryMethodQuery(new InverseComparator<Method>(new MethodParameterCountComparator()));

        List<Method> factoryMethods = this.query.getFactoryMethodsForType(SpecimenType.of(TypeWithFactoryMethod.class));

        assertEquals(expectedMethodTwoParameters, factoryMethods.get(0));
        assertEquals(expectedMethodOneParameter, factoryMethods.get(1));
    }

    @Test
    public void returns_factory_methods_where_return_type_is_assignable_to_declaring_type() {
        this.query = new DefaultFactoryMethodQuery();

        List<Method> factoryMethods = this.query.getFactoryMethodsForType(SpecimenType.of(AbstractTypeWithFactory.class));

        assertEquals(1, factoryMethods.size());
    }
}
