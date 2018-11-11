package component.behaviours;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.intercept.InterceptBehaviour;
import com.flextrade.jfixture.utility.Interceptor;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class TestInterceptingSpecimen {

    @Test
    public void specimen_can_be_intercepted_and_modified() {
        final String overriddenId = UUID.randomUUID().toString();
        JFixture fixture = new JFixture();
        fixture.behaviours().add(new InterceptBehaviour<Order>(Order.class, new Interceptor<Order>() {
            @Override
            public void intercept(Order instance) {
                instance.setId(overriddenId);
            }
        }));

        Order order = fixture.create(Order.class);
        assertEquals(overriddenId, order.getId());
    }

    @Test
    public void specimen_can_be_intercepted_fluently_and_modified() {
        final String overriddenId = UUID.randomUUID().toString();
        JFixture fixture = new JFixture();
        fixture.customise().intercept(Order.class, new Interceptor<Order>() {
            @Override
            public void intercept(Order instance) {
                instance.setId(overriddenId);
            }
        });

        Order order = fixture.create(Order.class);
        assertEquals(overriddenId, order.getId());
    }

    @Test
    public void specimen_is_only_intercepted_once() {
        final AtomicInteger interceptionCount = new AtomicInteger(0);
        JFixture fixture = new JFixture();
        fixture.customise().intercept(Order.class, new Interceptor<Order>() {
            @Override
            public void intercept(Order instance) {
                interceptionCount.incrementAndGet();
            }
        });

        fixture.create(Order.class);
        assertEquals(1, interceptionCount.get());
    }

    @Test
    public void null_specimen_is_ignored() {
        JFixture fixture = new JFixture();
        fixture.customise().sameInstance(Order.class, null);
        fixture.customise().intercept(Order.class, new Interceptor<Order>() {
            @Override
            public void intercept(Order instance) {
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
