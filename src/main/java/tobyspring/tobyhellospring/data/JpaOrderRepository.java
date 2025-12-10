package tobyspring.tobyhellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tobyspring.tobyhellospring.order.Order;
import tobyspring.tobyhellospring.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Order order) {
            em.persist(order);
    }
}
