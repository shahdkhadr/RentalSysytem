package com.rental.rental.services;

import com.rental.rental.dto.RegisterUserDto;
import com.rental.rental.model.Customer;
import com.rental.rental.model.Role;
import com.rental.rental.model.RoleEnum;
import com.rental.rental.repository.RoleRepository;
import com.rental.rental.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Customer> allUsers() {
        List<Customer> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Customer createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        // Creating a new Customer without the builder to avoid non-nullable field issues
        Customer admin = new Customer();
        admin.setCustomerName(input.getFullName());
        admin.setEmail(input.getEmail());
        admin.setPassword(passwordEncoder.encode(input.getPassword()));
        admin.setRole(optionalRole.get());

        return userRepository.save(admin);
    }
}
