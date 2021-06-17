package pl.warsztat.warsztat;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Admin";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.printf(encodedPassword);
    }
}
