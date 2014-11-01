package testtypes.factorymethods;

public abstract class AbstractTypeWithFactory {

    public static ConcreteType create() {
        return new ConcreteType();
    }
}
