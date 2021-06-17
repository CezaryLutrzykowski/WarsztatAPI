package pl.warsztat.warsztat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.warsztat.warsztat.model.Order;
import pl.warsztat.warsztat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByUsername(@Param("email") String email);

}
