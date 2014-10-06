package testtypes.constructors;

public class TypeWithConstructor {

    private final String symbol;
    private final int size;

    public TypeWithConstructor(String symbol, int size) {

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
