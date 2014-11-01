package testtypes.constructors;

public class ThreeConstructorType {
    private String i;
    private String j;
    private String k;

    public ThreeConstructorType(String i) {
        this.i = i;
    }

    public ThreeConstructorType(String i, String j) {
        this.i = i;
        this.j = j;
    }
    public ThreeConstructorType(String i, String j, String k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public String getI() {
        return i;
    }

    public String getJ() {
        return j;
    }

    public String getK() {
        return k;
    }
}
