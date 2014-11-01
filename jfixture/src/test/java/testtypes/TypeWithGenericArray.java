package testtypes;

import java.util.List;

public class TypeWithGenericArray {

    private final List<Double>[] ints;

    public TypeWithGenericArray(List<Double>[] ints, List<String> strings) { // Do not remove the List<String>
        this.ints = ints;
    }

    public List<Double>[] getInts() {
        return ints;
    }
}
