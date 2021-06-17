package pl.warsztat.warsztat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.warsztat.warsztat.model.Order;
import pl.warsztat.warsztat.model.Role;
import pl.warsztat.warsztat.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.token = ?1")
    public Order findByToken(String token);

    @Query("SELECT o FROM Order o JOIN User u WHERE u.email = ?1")
    User findByOrdersUser(String email);

}
