package pl.warsztat.warsztat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.warsztat.warsztat.model.Order;
import pl.warsztat.warsztat.repository.OrderRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OrderRepositoryTests {
    @Autowired
    OrderRepository repo;

    @Test
    public void testCreateOrders() {
        Order order1 = new Order("token1","Opel",100L,20L,20L,1999L,"2","all","Przyjete",200L,100L);
        Order order2 = new Order("token2","BMW",300L,40L,10L,2000L,"3.0","all","Przyjete",200L,100L);
        Order order3 = new Order("token3","BMW",302900L,400L,100L,1997L,"3.0","all","Przyjete",200L,100L);


        repo.saveAll(List.of(order1,order2,order3));

        List<Order> listOrders = repo.findAll();

        assertThat(listOrders.size()).isEqualTo(3);


    }
}
