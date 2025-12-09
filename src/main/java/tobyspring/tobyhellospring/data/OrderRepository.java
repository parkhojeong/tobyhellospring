package tobyspring.tobyhellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tobyspring.tobyhellospring.order.Order;

public class OrderRepository {
    @PersistenceContext
    EntityManager em;

    public void save(Order order) {
            em.persist(order);
    }
}
