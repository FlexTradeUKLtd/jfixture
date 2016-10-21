package testtypes.factorymethods;

public class GenericTypeWithCopyFactoryMethod<T> implements GenericInterfaceType<T> {

    private final T t;
    private final int size;
  
    private GenericTypeWithCopyFactoryMethod(T t, int size) {
        this.t = t;
        this.size = size;
    }
  
    public static <T> GenericTypeWithCopyFactoryMethod copy(GenericInterfaceType<T> source) {
        return new GenericTypeWithCopyFactoryMethod<T>(source.getT(), source.getSize());
    }

    public static <T> GenericTypeWithCopyFactoryMethod create(T t, int size) {
        return new GenericTypeWithCopyFactoryMethod<T>(t, size);
    }

    @Override 
    public T getT() {
        return t;
    }

    @Override 
    public int getSize() {
        return size;
    }
  
}
