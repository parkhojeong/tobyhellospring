package tobyspring.tobyhellospring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import tobyspring.tobyhellospring.order.Order;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        //em
        EntityManager em = emf.createEntityManager();

        //transaction
        em.getTransaction().begin();

        //em.persist
        Order order = new Order("100", BigDecimal.TEN);
        System.out.println(order);

        em.persist(order);

        System.out.println(order);

        em.getTransaction().commit();
        em.close();
    }
}
