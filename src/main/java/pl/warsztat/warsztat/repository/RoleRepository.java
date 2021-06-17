package pl.warsztat.warsztat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.warsztat.warsztat.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public Role findByName(String name);

}
