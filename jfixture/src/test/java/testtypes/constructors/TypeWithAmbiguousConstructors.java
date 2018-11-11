package testtypes.constructors;

import java.util.Collections;
import java.util.List;

/**
 * This type has multiple constructors, all of them would be picked first by JFixture due to their parameter count.
 */
public class TypeWithAmbiguousConstructors {
    private final List<String> list1;
    private final List<String> list2;

    public TypeWithAmbiguousConstructors(List<String> list1, List<String> list2) {
        this.list1 = list1;
        this.list2 = list2;
    }

    public TypeWithAmbiguousConstructors(List<String> list1, String item2) {
        this(list1, Collections.singletonList(item2));
    }

    public TypeWithAmbiguousConstructors(String item1, List<String> list2) {
        this(Collections.singletonList(item1), list2);
    }

    public TypeWithAmbiguousConstructors(String item1, String item2) {
        this(Collections.singletonList(item1), Collections.singletonList(item2));
    }

    public List<String> getList1() {
        return list1;
    }

    public List<String> getList2() {
        return list2;
    }
}
