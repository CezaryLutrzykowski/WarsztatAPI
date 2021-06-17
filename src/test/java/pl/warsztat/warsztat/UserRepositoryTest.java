package pl.warsztat.warsztat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import pl.warsztat.warsztat.model.Order;
import pl.warsztat.warsztat.model.Role;
import pl.warsztat.warsztat.model.User;
import pl.warsztat.warsztat.repository.OrderRepository;
import pl.warsztat.warsztat.repository.RoleRepository;
import pl.warsztat.warsztat.repository.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(value = false) //!
@Rollback(false) //!
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("Admin1@gmail.com");
        user.setHaslo("Admin1");
        user.setImie("Admin1");
        user.setNazwisko("Admin1");

        User savedUser = userRepo.save(user);

        User exitUser = entityManager.find(User.class, savedUser.getId());

        assertThat(exitUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail() {
        String email = "Admin1@gmail.com";

        User user = userRepo.findByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testAddRolesToExistingUser() {
        User user = userRepo.findById(1L).get();

        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        Role roleAdmin = new Role(2L);
        user.addRole(roleAdmin);

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }

    @Test
    public void testFindRolesToExistingUser() {
        User user = userRepo.findById(1L).get();


        //Role roleAdmin = roleRepo.findByName("Admin");

        if (user.getRoles().contains(roleRepo.findByName("Customer"))){
            System.out.println();
            System.out.println(user.getRoles());
            System.out.println("Udało sie znalesc");


//            user.getRoles().equals("Admin")

        }
        System.out.println();
        System.out.println("Test");
        System.out.println(user.getRoles());
    }

/*    @Test
    public void testAddOrderToExistingUser() {
        User user = userRepo.findById(1L).get();

        Order orderUser = orderRepo.findByToken("855639token1");
        user.addOrder(orderUser);


        User savedUser = userRepo.save(user);

        assertThat(savedUser.getOrders().size()).isEqualTo(1);

//        Order order1 = new Order("token","Opel",100L,20L,20L,1999L,"2.0");
//
//        repo.saveAll(List.of(order1));
//
//        List<Order> listOrders = repo.findAll();
//
//        assertThat(listOrders.size().isEqualTo(1));


    }*/
}

