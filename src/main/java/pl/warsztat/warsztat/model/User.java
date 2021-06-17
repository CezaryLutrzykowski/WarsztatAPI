package pl.warsztat.warsztat.model;

import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// klucz id bedzie auto inkrementowany po kolei (indywidualny numer dla kazdego)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String haslo;

    @Column(nullable = false, length = 20)
    private String imie;

    @Column(nullable = false, length = 20)
    private String nazwisko;

    @Column(length = 16)
    private Long telefon;

//    private Date data;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Set<Order> orders = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Long getTelefon () { return telefon; }

    public void setTelefon (Long telefon) { this.telefon = telefon; }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setOrders(Set<Order> orders) { this.orders = orders; }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public Set<Order> getOrders() { return orders; }

}
