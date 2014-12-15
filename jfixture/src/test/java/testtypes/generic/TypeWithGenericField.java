package testtypes.generic;

import java.util.List;
import java.util.Map;

public class TypeWithGenericField<T, U> {

    public T valueT;

    public List<T> genericListT;

    public List<U> genericListU;

    public List<List<T>> genericListOfListT;

    public List<List<Double>> genericTypedListOfList;

    public List<Map<T, U>> genericListOfMap;

    public Map<T, Map<Integer, U>> genericPartiallyBound;

    public List<Double> typedList;
}
