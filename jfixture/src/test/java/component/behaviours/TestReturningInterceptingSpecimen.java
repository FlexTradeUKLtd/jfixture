package component.behaviours;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.intercept.ReturningInterceptingBehaviour;
import com.flextrade.jfixture.utility.ReturningInterceptor;

public class TestReturningInterceptingSpecimen {

    @Test
    public void specimen_can_be_intercepted_and_new_value_is_used() {
        final Order overridingInstance = new Order();
        JFixture fixture = new JFixture();
        fixture.behaviours().add(new ReturningInterceptingBehaviour<Order>(Order.class, new ReturningInterceptor<Order>() {
            @Override
            public Order intercept(Order instance) {
                return overridingInstance;
            }
        }));

        Order order = fixture.create(Order.class);
        assertSame(overridingInstance, order);
    }

    @Test
    public void specimen_can_be_intercepted_fluently_and_new_value_is_used() {
        final Order overridingInstance = new Order();
        JFixture fixture = new JFixture();
        fixture.customise().interceptAndReturn(Order.class, new ReturningInterceptor<Order>() {
            @Override
            public Order intercept(Order instance) {
                return overridingInstance;
            }
        });

        Order order = fixture.create(Order.class);
        assertSame(overridingInstance, order);
    }

    @Test
    public void specimen_is_only_intercepted_once() {
        final Order overridingInstance = new Order();
        final AtomicInteger interceptionCount = new AtomicInteger(0);
        JFixture fixture = new JFixture();
        fixture.customise().interceptAndReturn(Order.class, new ReturningInterceptor<Order>() {
            @Override
            public Order intercept(Order instance) {
                interceptionCount.incrementAndGet();
                return overridingInstance;
            }
        });

        fixture.create(Order.class);
        assertEquals(1, interceptionCount.get());
    }

    @Test
    public void null_specimen_is_ignored() {
        JFixture fixture = new JFixture();
        fixture.customise().sameInstance(Order.class, null);
        fixture.customise().interceptAndReturn(Order.class, new ReturningInterceptor<Order>() {
            @Override
            public Order intercept(Order instance) {
                throw new RuntimeException("Specimen was erroneously intercepted");
            }
        });

        fixture.create(Order.class);
    }


    public static class Order {
        private String id;
        private int size;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
