package com.rental.rental.bootstrap;

import com.rental.rental.dto.RegisterUserDto;
import com.rental.rental.model.Customer;
import com.rental.rental.model.Role;
import com.rental.rental.model.RoleEnum;
import com.rental.rental.repository.RoleRepository;
import com.rental.rental.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Running AdminSeeders...");
        createSuperAdmin();
    }

    private void createSuperAdmin() {
        String adminEmail = "super.admin@email.com";
        RegisterUserDto userDto = RegisterUserDto.builder()
                .fullName("Super Admin")
                .email(adminEmail)
                .password("admin123")
                .build();

        Optional<Role> adminRole = roleRepository.findByName(RoleEnum.ADMIN);
        Optional<Customer> existingAdmin = userRepository.findByEmail(adminEmail);

        if (adminRole.isPresent() && existingAdmin.isEmpty()) {
            Customer admin = new Customer();
            admin.setCustomerName(userDto.getFullName());
            admin.setEmail(userDto.getEmail());
            admin.setPassword(passwordEncoder.encode(userDto.getPassword()));
            admin.setRole(adminRole.get());

            userRepository.save(admin);
        }
    }
}
