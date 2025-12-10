package tobyspring.tobyhellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.tobyhellospring.data.JdbcOrderRepository;
import tobyspring.tobyhellospring.order.OrderRepository;
import tobyspring.tobyhellospring.order.OrderService;
import tobyspring.tobyhellospring.order.OrderServiceImpl;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
@EnableTransactionManagement
public class OrderConfig {
    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        return new OrderServiceImpl(orderRepository);
    }
}
