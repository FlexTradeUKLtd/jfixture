package com.flextrade.jfixture;

import com.flextrade.jfixture.utility.SpecimenType;
import com.flextrade.jfixture.utility.comparators.InverseComparator;
import com.flextrade.jfixture.utility.comparators.MethodParameterCountComparator;
import org.junit.Before;
import org.junit.Test;
import testtypes.factorymethods.AbstractTypeWithFactory;
import testtypes.factorymethods.GenericTypeWithCopyFactoryMethod;
import testtypes.factorymethods.TypeWithCopyFactoryMethod;
import testtypes.factorymethods.TypeWithFactoryMethod;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestDefaultFactoryMethodQuery {

    private DefaultFactoryMethodQuery query;
    private Method expectedMethodOneParameter;
    private Method expectedMethodTwoParameters;
    private Method expectedMethodTwoParametersReverse;

    @Before
    public void initialise() throws NoSuchMethodException {
        expectedMethodOneParameter = TypeWithFactoryMethod.class.getMethod("create", String.class);
        expectedMethodTwoParameters = TypeWithFactoryMethod.class.getMethod("create", String.class, int.class);
        expectedMethodTwoParametersReverse = TypeWithFactoryMethod.class.getMethod("create", int.class, String.class);
    }

    @Test
    public void returns_all_factory_methods_for_a_given_class() {
        // Factory methods are public, static methods with return type assignable to declared type
        this.query = new DefaultFactoryMethodQuery();

        List<Method> factoryMethods = this.query.getFactoryMethodsForType(SpecimenType.of(TypeWithFactoryMethod.class));

        assertEquals(3, factoryMethods.size());
        assertTrue(factoryMethods.contains(expectedMethodOneParameter));
        assertTrue(factoryMethods.contains(expectedMethodTwoParameters));
        assertTrue(factoryMethods.contains(expectedMethodTwoParametersReverse));
    }

    @Test
    public void sorts_methods_using_given_comparator_ascending_parameter_count() {
        this.query = new DefaultFactoryMethodQuery(new MethodParameterCountComparator());

        List<Method> factoryMethods = this.query.getFactoryMethodsForType(SpecimenType.of(TypeWithFactoryMethod.class));

        assertEquals(expectedMethodOneParameter, factoryMethods.get(0));
        assertEquals(expectedMethodTwoParametersReverse, factoryMethods.get(1));
        assertEquals(expectedMethodTwoParameters, factoryMethods.get(2));
    }

    @Test
    public void sorts_methods_using_given_comparator_descending_parameter_count() {
        this.query = new DefaultFactoryMethodQuery(new InverseComparator<Method>(new MethodParameterCountComparator()));

        List<Method> factoryMethods = this.query.getFactoryMethodsForType(SpecimenType.of(TypeWithFactoryMethod.class));

        assertEquals(expectedMethodTwoParameters, factoryMethods.get(0));
        assertEquals(expectedMethodTwoParametersReverse, factoryMethods.get(1));
        assertEquals(expectedMethodOneParameter, factoryMethods.get(2));
    }

    @Test
    public void returns_factory_methods_where_return_type_is_assignable_to_declaring_type() {
        this.query = new DefaultFactoryMethodQuery();

        List<Method> factoryMethods = this.query.getFactoryMethodsForType(SpecimenType.of(AbstractTypeWithFactory.class));

        assertEquals(1, factoryMethods.size());
    }

    @Test
    public void ignores_factory_methods_where_a_singleton_parameter_is_assignable_from_return_type() {
        this.query = new DefaultFactoryMethodQuery();

        List<Method> factoryMethods = this.query.getFactoryMethodsForType(SpecimenType.of(TypeWithCopyFactoryMethod.class));

        assertEquals(1, factoryMethods.size());
    }

    @Test
    public void ignores_factory_methods_where_a_singleton_generic_parameter_is_assignable_from_return_type() {
        this.query = new DefaultFactoryMethodQuery();

        List<Method> factoryMethods = this.query.getFactoryMethodsForType(new SpecimenType<GenericTypeWithCopyFactoryMethod<String>>() {});

        assertEquals(1, factoryMethods.size());
    }
}
