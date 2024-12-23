package com.rental.rental.services;


import com.rental.rental.model.Customer;
import com.rental.rental.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch Customer by email (username)
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found: " + username));
    }
}
