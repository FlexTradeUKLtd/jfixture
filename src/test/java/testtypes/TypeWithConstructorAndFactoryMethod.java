package testtypes;

public class TypeWithConstructorAndFactoryMethod {

    private String ctorArg;
    private String methodArg;

    public TypeWithConstructorAndFactoryMethod(String ctorArg) {
        this.ctorArg = ctorArg;
    }

    private TypeWithConstructorAndFactoryMethod() { }

    public static TypeWithConstructorAndFactoryMethod create(String methodArg) {
        TypeWithConstructorAndFactoryMethod instance = new TypeWithConstructorAndFactoryMethod();
        instance.methodArg = methodArg;

        return instance;
    }

    public String getCtorArg() {
        return ctorArg;
    }

    public String getMethodArg() {
        return methodArg;
    }
}
