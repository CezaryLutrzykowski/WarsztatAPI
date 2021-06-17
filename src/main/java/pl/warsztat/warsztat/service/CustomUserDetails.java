package pl.warsztat.warsztat.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.warsztat.warsztat.model.Role;
import pl.warsztat.warsztat.model.User;


public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getHaslo();
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return user.getImie() + " " + user.getNazwisko();
    }

    public String getImie() {
        return " " + user.getImie();
    }

    public String getNazwisko() {
        return " " + user.getNazwisko();
    }

    public String getTelefon() {
        if (user.getTelefon() == null) {
            return " ";
        }
        return " " + user.getTelefon();
    }

    public void setImie(String imie) {
        this.user.setImie(imie);
    }
    public void setNazwisko(String nazwisko) {
        this.user.setImie(nazwisko);
    }
    public void setTelefon(Long telefon) {
        this.user.setTelefon(telefon);
    }

//    public void deteleUser(Long id) {
//        this.user.
//    }

}