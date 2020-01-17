package com.flextrade.jfixture;

import com.flextrade.jfixture.utility.comparators.ConstructorParameterCountComparator;
import com.flextrade.jfixture.utility.comparators.InverseComparator;
import org.junit.Before;
import org.junit.Test;
import testtypes.constructors.TwoConstructorType;
import testtypes.constructors.TypeWithMultipleConstructorsHavingSameParameterCount;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestDefaultConstructorQuery {

    private DefaultConstructorQuery query;
    private Constructor<?> expectedConstructorOneParameter;
    private Constructor<?> expectedConstructorTwoParameters;

    @Before
    public void initialise() throws NoSuchMethodException {
        expectedConstructorOneParameter = TwoConstructorType.class.getConstructor(String.class);
        expectedConstructorTwoParameters = TwoConstructorType.class.getConstructor(String.class, String.class);
    }

    @Test
    public void returns_all_constructors_for_a_given_class() {
        this.query = new DefaultConstructorQuery();

        List<Constructor<?>> constructors = this.query.getConstructorsForClass(TwoConstructorType.class);

        assertEquals(2, constructors.size());
        assertTrue(constructors.contains(expectedConstructorOneParameter ));
        assertTrue(constructors.contains(expectedConstructorTwoParameters));
    }

    @Test
    public void sorts_constructors_using_given_comparator_ascending_parameter_count() {
        this.query = new DefaultConstructorQuery(new ConstructorParameterCountComparator());

        List<Constructor<?>> constructors = this.query.getConstructorsForClass(TwoConstructorType.class);

        assertEquals(2, constructors.size());
        assertEquals(expectedConstructorOneParameter, constructors.get(0));
        assertEquals(expectedConstructorTwoParameters, constructors.get(1));
    }

    @Test
    public void sorts_constructors_using_given_comparator_descending_parameter_count() {
        this.query = new DefaultConstructorQuery(new InverseComparator<Constructor<?>>(new ConstructorParameterCountComparator()));

        List<Constructor<?>> constructors = this.query.getConstructorsForClass(TwoConstructorType.class);

        assertEquals(2, constructors.size());
        assertEquals(expectedConstructorTwoParameters, constructors.get(0));
        assertEquals(expectedConstructorOneParameter, constructors.get(1));
    }

    @Test
    public void sort_by_signature_when_constructors_have_same_number_of_parameters() throws NoSuchMethodException {
        this.query = new DefaultConstructorQuery(new ConstructorParameterCountComparator());

        Class<?> targetClass = TypeWithMultipleConstructorsHavingSameParameterCount.class;
        List<Constructor<?>> constructors = this.query.getConstructorsForClass(targetClass);

        assertEquals(4, constructors.size());
        assertEquals(targetClass.getConstructor(String.class, String.class), constructors.get(0));
        assertEquals(targetClass.getConstructor(String.class, List.class), constructors.get(1));
        assertEquals(targetClass.getConstructor(List.class, String.class), constructors.get(2));
        assertEquals(targetClass.getConstructor(List.class, List.class), constructors.get(3));
    }
}
