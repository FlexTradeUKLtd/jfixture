package testtypes.constructors;

public class TwoConstructorType {
    private final String i;
    private final String j;

    public TwoConstructorType(String i) {
        this.i = i;
        this.j = null;
    }
    public TwoConstructorType(String i, String j) {
        this.i = i;
        this.j = j;
    }

    public String getI() {
        return i;
    }

    public String getJ() {
        return j;
    }
}