package pl.warsztat.warsztat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.warsztat.warsztat.model.Order;
import pl.warsztat.warsztat.model.Role;
import pl.warsztat.warsztat.model.User;
import pl.warsztat.warsztat.service.CustomUserDetails;
import pl.warsztat.warsztat.service.UserService;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserService service;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("index")
    public String viewHomePageBack() {
        return "index";
    }

    @GetMapping("style")
    public String cssstyle() {
        return "style";
    }


    @GetMapping("/zarejestruj")
    public String showSignUpForm(Model model) { //?
        model.addAttribute("user", new User()); //?
        return "zarejestruj";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        service.saveUserWithDefaultRole(user);
        return "register_success";
    }
    @PostMapping("/addOrder")
    public String addOrder(Order order) {
        order.setToken("token");
        service.saveOrder(order);

        return "redirect:/history";
    }

    @GetMapping("/zaloguj")
    public String viewPageLogin() {
        return "index";
    }

    @GetMapping("/active_order")
    public String activeOrder(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        long id = loggedUser.getId();
        User user = service.get(id);


//        List<Order> listOrderUser = user.getOrders();
//        List<Order> listOrder = service.getOrderUser();

        model.addAttribute("listOrderUser", user.getOrders());

        return "active_order";
    }

    @GetMapping("/history")
    public String history(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model) {
        long id = loggedUser.getId();
        List<Order> listOrder = service.getOrder();
        model.addAttribute("listOrder", listOrder);

        if (service.findAdminCustomerUser(id)) {
            return "history_admin";
        }else {
            return "history";
        }
    }
    @GetMapping({"/order/edit/{id}"})
    public String editOrder(@PathVariable("id") Long id, Model model) {
        Order order = this.service.getOrderId(id);
        model.addAttribute("order", order);
        return "mh_order";
    }

    @PostMapping({"/orders/save"})
    public String saveOrder(Order order) {
        this.service.updataOrder(order);
        return "redirect:/history";
    }


    @GetMapping("/user-s")
    public String manage_user(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        long id = loggedUser.getId();

        if (service.findAdminCustomerUser(id)) {
            List<User> listUsers = service.listAll();
            model.addAttribute("listUsers", listUsers);
            return "users";


        }else{
            User user = service.get(id);
            List<Role> listRoles = service.getRoles();

            model.addAttribute("user", user);
            model.addAttribute("listRoles", listRoles);
            return "manage_user";
        }
    }

//    @GetMapping("/users")
//    public String users(Model model) {
//
//        return "users";
//    }

    @GetMapping("/users_conf")
    public String users_conf() {
        return "users_conf";
    }

    @PostMapping("/manage_user/update")
    public String saveUser(@AuthenticationPrincipal CustomUserDetails loggedUser,User user)  {

        service.saveUserWithDefaultRole(user);

        loggedUser.setImie(user.getImie());
        loggedUser.setNazwisko(user.getNazwisko());
        loggedUser.setTelefon(user.getTelefon());

//        return "active_order";
        return "redirect:/active_order";
    }

    @GetMapping("/token_order")
    public String token_panel(@AuthenticationPrincipal CustomUserDetails loggedUser, Order order) {
        long id = loggedUser.getId();
        User user = service.get(id);

        if (service.findAdminCustomerUser(id)) {
            return "add_order";

        }else{
            return "token_panel";
        }
    }

//    @GetMapping("/token_panel")
//    public String token_panel(@AuthenticationPrincipal CustomUserDetails loggedUser, Order order) {
//        long id = loggedUser.getId();
//        User user = service.get(id);
//
//        return "token_panel";
//    }
//
//    @GetMapping("/add_order")
//    public String add_order(Order order) {
//        return "add_order";
//    }

    @PostMapping("/add_token")
    public String addToken(@AuthenticationPrincipal CustomUserDetails loggedUser, Order order) {
        service.saveTokenToUser(order,loggedUser.getId());

        return "redirect:/active_order";
    }



    @GetMapping("/uslugi")
    public String uslugi() {
        return "uslugi";
    }

    @GetMapping("/mechanika")
    public String mechanika() {
        return "mechanika";
    }

    @GetMapping("/mh_order")
    public String mh_order() {
        return "mh_order";
    }




    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = service.get(id);
        List<Role> listRoles = service.getRoles();

        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);

        return "users_conf";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        service.save(user);

        return "redirect:/user-s";
    }

    @RequestMapping("/delete_order/{id}")
    public String delete_order(@PathVariable(name = "id") Long id ) {
        service.deleteOrder(id);

        return "redirect:/history";
    }

    @RequestMapping("/users/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id ) {
        service.deleteUser(id);

        return "redirect:/user-s";
    }

//    @GetMapping("/403")
//    public String error403() {
//        return "403";
//    }


}
