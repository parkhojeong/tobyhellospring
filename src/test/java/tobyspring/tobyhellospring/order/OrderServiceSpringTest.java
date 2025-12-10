package tobyspring.tobyhellospring.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.tobyhellospring.OrderConfig;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Test
    public void createOrder() throws Exception {
        //given

        //when
        var order = orderService.createOrder("100", BigDecimal.ONE);

        //then
        Assertions.assertThat(order.getId()).isNotNull();
    }

}
