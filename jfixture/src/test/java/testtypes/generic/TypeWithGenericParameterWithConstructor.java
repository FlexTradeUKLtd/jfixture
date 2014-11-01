package testtypes.generic;

public class TypeWithGenericParameterWithConstructor<T> {

    private final String symbol;
    private final int size;

    public TypeWithGenericParameterWithConstructor(String symbol, int size) {

        this.symbol = symbol;
        this.size = size;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getSize() {
        return size;
    }
}
