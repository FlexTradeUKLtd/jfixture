package testtypes.generic;

public class TypeWithGenericFactoryMethod<T> {

    private final T value;

    private TypeWithGenericFactoryMethod(T value) {

        this.value = value;
    }

    public static <T> TypeWithGenericFactoryMethod<T> create(T value) {
        return new TypeWithGenericFactoryMethod<T>(value);
    }

    public T getValue() {
        return value;
    }
}
