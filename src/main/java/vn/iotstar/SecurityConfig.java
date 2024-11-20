package vn.iotstar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

 
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("trung")
                .password(encoder.encode("123")) 
                .roles("ADMIN") 
                .build();

        UserDetails user = User.withUsername("user")
                .password(encoder.encode("123")) 
                .roles("USER") 
                .build();

        
        return new InMemoryUserDetailsManager(admin, user);
    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF (nếu không cần thiết)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll() // Cho phép tất cả truy cập vào "/"
                        .requestMatchers("/customer/**").authenticated() // Yêu cầu authentication cho "/customer/**"
                        //.anyRequest().authenticated() // Yêu cầu authentication cho các request khác (nếu cần)
                )
                .formLogin(form -> form.loginPage("/login").permitAll()) // Sử dụng form login mặc định của Spring
                .build();
    }
    
}
