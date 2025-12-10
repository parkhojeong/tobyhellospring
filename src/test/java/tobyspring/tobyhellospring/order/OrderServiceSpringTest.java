package tobyspring.tobyhellospring.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.tobyhellospring.OrderConfig;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSource;

    @Test
    public void createOrder() throws Exception {
        //given

        //when
        var order = orderService.createOrder("100", BigDecimal.ONE);

        //then
        Assertions.assertThat(order.getId()).isNotNull();
    }

    @Test
    public void createOrders() throws Exception {
        //given
        var reqs = List.of(
                new OrderReq("100", BigDecimal.ONE),
                new OrderReq("200", BigDecimal.TEN)
        );

        //when
        var orders = orderService.createOrders(reqs);

        //then
        Assertions.assertThat(orders).hasSize(2);
        orders.forEach(order -> Assertions.assertThat(order.getId()).isNotNull());
    }

    @Test
    public void createDuplicatedOrders() throws Exception {
        //given
        var reqs = List.of(
                new OrderReq("100", BigDecimal.ONE),
                new OrderReq("100", BigDecimal.TEN)
        );

        //when & then
        Assertions.assertThatThrownBy(() -> orderService.createOrders(reqs))
                .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(dataSource);
        var count = client.sql("select count(*) from orders where no = '100'")
                .query(Long.class)
                .single();
        Assertions.assertThat(count).isEqualTo(0);
    }
}
