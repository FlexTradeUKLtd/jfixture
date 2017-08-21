package component.generics;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.utility.SpecimenType;

public class TestInheritanceWithGenerics {

  @Test
  public void non_generic_class_inheriting_a_generic_class_should_fixture_the_generic_types_correctly() {
    JFixture fixture = new JFixture();
    ClassInheritingGenericClass newType = fixture.create(ClassInheritingGenericClass.class);
    assertNotNull(newType.getField());
    assertThat(newType.getField(), instanceOf(String.class));
  }

  @Test
  public void non_generic_class_inheriting_a_generic_class_further_down_the_inheritance_chain_should_fixture_correctly() {
    JFixture fixture = new JFixture();
    NestedClassInheritingGenericClass newType = fixture.create(NestedClassInheritingGenericClass.class);
    assertNotNull(newType.getField());
    assertThat(newType.getField(), instanceOf(String.class));
  }

  @Test
  public void generic_class_inheriting_another_generic_class_should_fixture_correctly() {
    JFixture fixture = new JFixture();
    GenericClassExtendingGenericClass<Integer> newType = fixture.create(new SpecimenType<GenericClassExtendingGenericClass<Integer>>(){});
    assertNotNull(newType.getField());
    assertThat(newType.getField(), instanceOf(Integer.class));
    assertNotNull(newType.getOtherField());
    assertThat(newType.getOtherField(), instanceOf(Integer.class));
  }

  @Test
  public void generic_class_inheriting_non_generic_class_inheriting_generic_class_should_fixture_correctly() {
    JFixture fixture = new JFixture();
    GenericClassExtendingClassInheritingGenericClass<Integer> newType = fixture.create(new SpecimenType<GenericClassExtendingClassInheritingGenericClass<Integer>>(){});
    assertNotNull(newType.getField());
    assertThat(newType.getField(), instanceOf(String.class));
    assertNotNull(newType.getOtherField());
    assertThat(newType.getOtherField(), instanceOf(Integer.class));
  }


  public class GenericClass<T> {
    protected T field;
    public T getField() { return this.field; }
    public void setField(T field) { this.field = field; }
  }

  public class ClassInheritingGenericClass extends GenericClass<String> {}

  public class NestedClassInheritingGenericClass extends ClassInheritingGenericClass {}

  public class GenericClassExtendingGenericClass<S> extends GenericClass<S> {
    private S otherField;
    public S getOtherField() { return otherField; }
    public void setOtherField(S otherField) { this.otherField = otherField; }
  }

  public class GenericClassExtendingClassInheritingGenericClass<S> extends ClassInheritingGenericClass {
    private S otherField;
    public S getOtherField() { return otherField; }
    public void setOtherField(S otherField) { this.otherField = otherField; }
  }

}
