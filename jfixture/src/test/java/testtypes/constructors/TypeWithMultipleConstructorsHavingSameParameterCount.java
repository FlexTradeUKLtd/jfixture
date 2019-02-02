package testtypes.constructors;

import java.util.Collections;
import java.util.List;

public class TypeWithMultipleConstructorsHavingSameParameterCount {
    private final List<String> list1;
    private final List<String> list2;

    public TypeWithMultipleConstructorsHavingSameParameterCount(List<String> list1, List<String> list2) {
        this.list1 = list1;
        this.list2 = list2;
    }

    public TypeWithMultipleConstructorsHavingSameParameterCount(List<String> list1, String item2) {
        this(list1, Collections.singletonList(item2));
    }

    public TypeWithMultipleConstructorsHavingSameParameterCount(String item1, List<String> list2) {
        this(Collections.singletonList(item1), list2);
    }

    public TypeWithMultipleConstructorsHavingSameParameterCount(String item1, String item2) {
        this(Collections.singletonList(item1), Collections.singletonList(item2));
    }

    public List<String> getList1() {
        return list1;
    }

    public List<String> getList2() {
        return list2;
    }
}
