package com.rental.rental.services;

import com.rental.rental.dto.LoginUserDto;
import com.rental.rental.dto.RegisterUserDto;
import com.rental.rental.model.Customer;
import com.rental.rental.model.Role;
import com.rental.rental.model.RoleEnum;
import com.rental.rental.repository.RoleRepository;
import com.rental.rental.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Customer signup(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        // Creating a new Customer without the builder to avoid non-nullable field issues
        Customer customer = new Customer();
        customer.setCustomerName(input.getFullName());
        customer.setEmail(input.getEmail());
        customer.setPassword(passwordEncoder.encode(input.getPassword()));
        customer.setRole(optionalRole.get());

        return userRepository.save(customer);
    }

    public Customer authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
