package testtypes.generic;

import java.util.List;
import java.util.Map;

public class TypeWithGenericMethod<T, U> {

    private T valueT;
    private List<T> genericListT;
    private List<U> genericListU;
    private List<List<T>> genericListOfListT;
    private List<List<Double>> genericTypesListOfList;
    private List<Double> typedList;
    private List<Map<T, U>> genericListOfMap;
    private Map<T, Map<Integer, U>> genericPartiallyBound;

    public T getValueT() {
        return valueT;
    }

    public void setValueT(T value) {
        this.valueT = value;
    }

    public List<T> getGenericListT() {
        return genericListT;
    }

    public void setGenericListT(List<T> list) {
        this.genericListT = list;
    }

    public List<Double> getTypedList() {
        return typedList;
    }

    public void setTypedList(List<Double> typedList) {
        this.typedList = typedList;
    }

    public List<U> getGenericListU() {
        return genericListU;
    }

    public void setGenericListU(List<U> list) {
        this.genericListU = list;
    }

    public List<List<T>> getGenericListOfListT() {
        return genericListOfListT;
    }

    public void setGenericListOfListT(List<List<T>> genericListOfListT) {
        this.genericListOfListT = genericListOfListT;
    }

    public List<List<Double>> getGenericTypesListOfList() {
        return genericTypesListOfList;
    }

    public void setGenericTypesListOfList(List<List<Double>> genericTypesListOfList) {
        this.genericTypesListOfList = genericTypesListOfList;
    }

    public List<Map<T, U>> getGenericListOfMap() {
        return genericListOfMap;
    }

    public void setGenericListOfMap(List<Map<T, U>> genericListOfMap) {
        this.genericListOfMap = genericListOfMap;
    }

    public Map<T, Map<Integer, U>> getGenericPartiallyBound() {
        return genericPartiallyBound;
    }

    public void setGenericPartiallyBound(Map<T, Map<Integer, U>> genericPartiallyBound) {
        this.genericPartiallyBound = genericPartiallyBound;
    }
}
