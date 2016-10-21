package testtypes.factorymethods;

public class TypeWithCopyFactoryMethod implements InterfaceType {

    private final String symbol;
    private final int size;
  
    private TypeWithCopyFactoryMethod(String symbol, int size) {
        this.symbol = symbol;
        this.size = size;
    }
  
    public static TypeWithCopyFactoryMethod copy(InterfaceType source) {
        return new TypeWithCopyFactoryMethod(source.getSymbol(), source.getSize());
    }

    public static TypeWithCopyFactoryMethod create(String symbol, int size) {
        return new TypeWithCopyFactoryMethod(symbol, size);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int getSize() {
        return size;
    }
  
}
