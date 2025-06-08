import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGen {
    public static void main(String[] args) {
        String senha = args.length > 0 ? args[0] : "1234567890";
        System.out.println(new BCryptPasswordEncoder().encode(senha));
    }
}
