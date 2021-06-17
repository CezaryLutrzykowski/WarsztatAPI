package pl.warsztat.warsztat.service;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.warsztat.warsztat.model.Order;
import pl.warsztat.warsztat.model.Role;
import pl.warsztat.warsztat.model.User;
import pl.warsztat.warsztat.repository.OrderRepository;
import pl.warsztat.warsztat.repository.RoleRepository;
import pl.warsztat.warsztat.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private OrderRepository orderRepo;

    public void saveUserWithDefaultRole(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getHaslo());
        user.setHaslo(encodedPassword);

        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        userRepo.save(user);
    }

    public void save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getHaslo());
        user.setHaslo(encodedPassword);
        userRepo.save(user);
    }

    public void saveOrder(Order order) {
//        order.setToken();
        Random random = new Random();
        order.setToken(random.nextInt(999999) + order.getToken());
        orderRepo.save(order);
    }

    public void updataOrder(Order order) {
        this.orderRepo.save(order);
    }

    public void saveTokenToUser(Order order, Long id) {
        Order orderUser = orderRepo.findByToken(order.getToken());

        User user = userRepo.findById(id).get();
        user.addOrder(orderUser);

        userRepo.save(user);
    }

//    public List<Order> getOrderUser(Long id) {
//        User user = userRepo.findById(id).get();
//
//        return orderRepo.findByOrdersUser(user.getOrders());
//    }

    public void ddOrderToExistingUser(Long id, String token) {
        User user = userRepo.findById(id).get();

        Order orderUser = orderRepo.findByToken(token);
        user.addOrder(orderUser);

        //User savedUser = userRepo.save(user);
        userRepo.save(user);
    }

    public List<User> listAll() {
        return userRepo.findAll();
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

//    public List<Order> listAll() {return orderRepo.findAll();

//    }


    public User get(Long id) {
        return userRepo.findById(id).get();
    }

    public List<Role> getRoles() {
        return roleRepo.findAll();
    }

    public List<Order> getOrder() {
        return orderRepo.findAll();
    }

    public boolean findAdminCustomerUser(Long id) {

        User user = userRepo.findById(id).get();

        if (user.getRoles().contains(roleRepo.findByName("Admin")) || user.getRoles().contains(roleRepo.findByName("Customer"))) {
            return true;
        } else {
            return false;
        }
    }

    //    public List<Order> getOrderUser() {
//        return (List<Order>) orderRepo.findByOrdersUser("Admin123@gmail.com");
//    }
    public Order get(String token) {
        return orderRepo.findByToken(token);
    }

    public Order getOrderId(Long id) {
        return (Order) this.orderRepo.findById(id).get();
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

}