package testtypes.generic;

public class TypeWithGenericConstructor<T, U> {

    private final T tValue;
    private final U uValue;

    // The "T" and "U" parameters have been switched deliberately to ensure it handles that
    public TypeWithGenericConstructor(U uValue, Double aDouble, T tValue) {
        this.tValue = tValue;
        this.uValue = uValue;
    }

    public T gettValue() {
        return tValue;
    }

    public U getuValue() {
        return uValue;
    }
}
