package tobyspring.tobyhellospring.data;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.jdbc.core.simple.JdbcClient;
import tobyspring.tobyhellospring.order.Order;
import tobyspring.tobyhellospring.order.OrderRepository;

import javax.sql.DataSource;

public class JdbcOrderRepository implements OrderRepository {
    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    @PostConstruct
    public void init() {
        jdbcClient.sql("""
            create table orders (id bigint not null, no varchar(255), total number(38,2), primary key (id));
           alter table if exists orders drop constraint if exists UK43egxxciqr9ncgmxbdx2avi8n;
           alter table if exists orders add constraint UK43egxxciqr9ncgmxbdx2avi8n unique (no);
           create sequence orders_SEQ start with 1 increment by 50;
        """).update();
    }

    @Override
    public void save(Order order) {
        Long nextOrderId = jdbcClient.sql("select next value for orders_SEQ")
                .query(Long.class)
                .single();
        order.setId(nextOrderId);
        jdbcClient.sql("insert into orders(no, total, id) values(?,?,?)")
                .params(order.getNo(), order.getTotal(), order.getId())
                .update();
    }
}
