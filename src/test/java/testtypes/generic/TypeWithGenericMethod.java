package testtypes.generic;

public class TypeWithGenericMethod<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
