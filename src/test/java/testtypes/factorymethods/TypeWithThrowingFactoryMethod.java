package testtypes.factorymethods;

public class TypeWithThrowingFactoryMethod {

    private TypeWithThrowingFactoryMethod(String symbol) {
    }

    public static TypeWithThrowingFactoryMethod create() {
        throw new RuntimeException();
    }

    public static TypeWithThrowingFactoryMethod create(String symbol) {
        return new TypeWithThrowingFactoryMethod(symbol);
    }
}
