package com.aldahirzamora.personal_manager_expense_backend.auth.config;

import com.aldahirzamora.personal_manager_expense_backend.auth.entity.Role;
import com.aldahirzamora.personal_manager_expense_backend.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        seed("USER", "Usuario estandar de la aplicacion");
        seed("ADMIN", "Administrador con acceso total");
    }

    private void seed(String name, String description) {
        if (roleRepository.findByName(name).isEmpty()) {
            roleRepository.save(Role.builder().name(name).description(description).build());
        }
    }
}
