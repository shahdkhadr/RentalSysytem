package com.rental.rental.bootstrap;

import com.rental.rental.model.Role;
import com.rental.rental.model.RoleEnum;
import com.rental.rental.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Running RoleSeeder...");
        loadRoles();
    }

    private void loadRoles() {
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.ADMIN, "Administrator role"
        );

        Arrays.stream(RoleEnum.values()).forEach(roleName -> {
            Optional<Role> role = roleRepository.findByName(roleName);
            if (role.isEmpty()) {
                Role newRole = Role.builder()
                        .name(roleName)
                        .description(roleDescriptionMap.get(roleName))
                        .build();
                roleRepository.save(newRole);
            }
        });
    }
}
