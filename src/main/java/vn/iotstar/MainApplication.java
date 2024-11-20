package vn.iotstar;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.iotstar.entity.Role;
import vn.iotstar.repository.RoleRepository;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Test findByName method
        Optional<Role> role = roleRepository.findByName("USER");

        if (role.isPresent()) {
            System.out.println("Role found: " + role.get().getName());
        } else {
            System.out.println("Role not found");
        }
    }
}
