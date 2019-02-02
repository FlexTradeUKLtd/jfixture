package component.generics;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.flextrade.jfixture.JFixture;

public class TestSelfReferentialTypes {

  @Test
  public void classes_with_self_referential_generic_parameters_can_be_created() {
    assertNotNull(new JFixture().create(MyType.class));
  }

  public class SelfReferentialType<T extends SelfReferentialType<T>> {}

  public class MyType extends SelfReferentialType<MyType> {}
}
