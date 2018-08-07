package component.behaviours;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.intercept.ReturningInterceptBehaviour;
import com.flextrade.jfixture.utility.ReturningInterceptor;

public class TestReturningInterceptingSpecimen {

    @Test
    public void specimen_can_be_intercepted_and_modified() {
        final String overriddenId = UUID.randomUUID().toString();
        JFixture fixture = new JFixture();
        fixture.behaviours().add(new ReturningInterceptBehaviour<Order>(Order.class, new ReturningInterceptor<Order>() {
            @Override
            public Order intercept(Order instance) {
                instance.setId(overriddenId);
                return instance;
            }
        }));

        Order order = fixture.create(Order.class);
        assertEquals(overriddenId, order.getId());
    }

    @Test
    public void specimen_can_be_intercepted_fluently_and_modified() {
        final String overriddenId = UUID.randomUUID().toString();
        JFixture fixture = new JFixture();
        fixture.customise().intercept(Order.class, new ReturningInterceptor<Order>() {
            @Override
            public Order intercept(Order instance) {
                instance.setId(overriddenId);
                return instance;
            }
        });

        Order order = fixture.create(Order.class);
        assertEquals(overriddenId, order.getId());
    }

    @Test
    public void specimen_is_only_intercepted_once() {
        final AtomicInteger interceptionCount = new AtomicInteger(0);
        JFixture fixture = new JFixture();
        fixture.customise().intercept(Order.class, new ReturningInterceptor<Order>() {
            @Override
            public Order intercept(Order instance) {
                interceptionCount.incrementAndGet();
                return instance;
            }
        });

        fixture.create(Order.class);
        assertEquals(1, interceptionCount.get());
    }

    @Test
    public void null_specimen_is_ignored() {
        JFixture fixture = new JFixture();
        fixture.customise().sameInstance(Order.class, null);
        fixture.customise().intercept(Order.class, new ReturningInterceptor<Order>() {
            @Override
            public Order intercept(Order instance) {
                throw new RuntimeException("Specimen was erroneously intercepted");
            }
        });

        fixture.create(Order.class);
    }

    @Test
    public void returned_specimen_is_used() {
        final Order order = new Order();
        JFixture fixture = new JFixture();
        fixture.customise().intercept(Order.class, new ReturningInterceptor<Order>() {
            @Override
            public Order intercept(Order unused) {
                return order;
            }
        });

        Order fixturedOrder = fixture.create(Order.class);
        assertSame(fixturedOrder, order);
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
