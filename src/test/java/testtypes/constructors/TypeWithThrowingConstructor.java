package testtypes.constructors;

public class TypeWithThrowingConstructor {

    public TypeWithThrowingConstructor() {
        throw new RuntimeException();
    }

    public TypeWithThrowingConstructor(String string) {
    }
}
