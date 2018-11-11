package component.behaviours;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.intercept.TransformingBehaviour;
import com.flextrade.jfixture.utility.Transformer;

public class TestTransformerSpecimen {

    @Test
    public void specimen_can_be_transformed() {
        final Order overridingInstance = new Order();
        JFixture fixture = new JFixture();
        fixture.behaviours().add(new TransformingBehaviour<Order>(Order.class, new Transformer<Order>() {
            @Override
            public Order intercept(Order instance) {
                return overridingInstance;
            }
        }));

        Order order = fixture.create(Order.class);
        assertSame(overridingInstance, order);
    }

    @Test
    public void specimen_can_be_transformed_fluently() {
        final Order overridingInstance = new Order();
        JFixture fixture = new JFixture();
        fixture.customise().transform(Order.class, new Transformer<Order>() {
            @Override
            public Order intercept(Order instance) {
                return overridingInstance;
            }
        });

        Order order = fixture.create(Order.class);
        assertSame(overridingInstance, order);
    }

    @Test
    public void specimen_is_only_transformed_once() {
        final Order overridingInstance = new Order();
        final AtomicInteger interceptionCount = new AtomicInteger(0);
        JFixture fixture = new JFixture();
        fixture.customise().transform(Order.class, new Transformer<Order>() {
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
        fixture.customise().transform(Order.class, new Transformer<Order>() {
            @Override
            public Order intercept(Order instance) {
                throw new RuntimeException("Specimen was erroneously transformed");
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
