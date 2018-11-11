package com.flextrade.jfixture.utility;

import com.flextrade.jfixture.utility.comparators.ConstructorParameterCountComparator;
import org.junit.Before;
import org.junit.Test;
import testtypes.constructors.TwoConstructorType;
import testtypes.constructors.TypeWithAmbiguousConstructors;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestConstructorParameterCountComparator {

    private ConstructorParameterCountComparator comparator;
    private Constructor<TwoConstructorType> many;
    private Constructor<TwoConstructorType> few;

    @Before
    public void initialise() throws NoSuchMethodException {
        this.comparator = new ConstructorParameterCountComparator();

        this.few = TwoConstructorType.class.getConstructor(String.class);
        this.many = TwoConstructorType.class.getConstructor(String.class, String.class);
    }

    @Test
    public void returns_less_than_0_if_first_constructor_has_fewer_parameters_than_second() {
        int result = this.comparator.compare(few, many);
        assertTrue(result < 0);
    }

    @Test
    public void returns_greater_than_0_if_first_constructor_has_more_parameters_than_second() {
        int result = this.comparator.compare(many, few);
        assertTrue(result > 0);
    }

    @Test
    public void returns_0_if_constructors_have_same_number_of_parameters() {
        int result = this.comparator.compare(few, few);
        assertTrue(result == 0);
    }

    @Test
    public void returns_non_0_if_constructors_have_same_number_of_parameters_but_overloaded() throws NoSuchMethodException {
        Constructor<?> stringString = TypeWithAmbiguousConstructors.class.getConstructor(String.class, String.class);
        Constructor<?> stringList = TypeWithAmbiguousConstructors.class.getConstructor(String.class, List.class);

        int result = this.comparator.compare(stringString, stringList);

        // String,String <> String,List
        assertTrue(result != 0);
    }

    @Test
    public void returns_less_than_0_if_constructors_have_same_number_of_parameters_but_lexicographically_different() throws NoSuchMethodException {
        Constructor<?> stringList = TypeWithAmbiguousConstructors.class.getConstructor(String.class, List.class);
        Constructor<?> listString = TypeWithAmbiguousConstructors.class.getConstructor(List.class, String.class);

        int result = this.comparator.compare(stringList, listString);

        // java.lang.String,... < java.util.List<String>,...
        assertTrue(result < 0);
    }

    @Test
    public void returns_greater_than_0_if_constructors_have_same_number_of_parameters_but_lexicographically_different() throws NoSuchMethodException {
        Constructor<?> stringList = TypeWithAmbiguousConstructors.class.getConstructor(String.class, List.class);
        Constructor<?> listString = TypeWithAmbiguousConstructors.class.getConstructor(List.class, String.class);

        // java.util.List<String>,... > java.lang.String,...
        int result = this.comparator.compare(listString, stringList);

        assertTrue(result > 0);
    }
}
