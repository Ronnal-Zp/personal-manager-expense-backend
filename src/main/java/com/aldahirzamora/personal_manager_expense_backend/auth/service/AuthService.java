package com.aldahirzamora.personal_manager_expense_backend.auth.service;

import com.aldahirzamora.personal_manager_expense_backend.auth.dto.AuthResponse;
import com.aldahirzamora.personal_manager_expense_backend.auth.dto.LoginRequest;
import com.aldahirzamora.personal_manager_expense_backend.auth.dto.RegisterRequest;
import com.aldahirzamora.personal_manager_expense_backend.auth.entity.Role;
import com.aldahirzamora.personal_manager_expense_backend.auth.entity.User;
import com.aldahirzamora.personal_manager_expense_backend.auth.exception.UserAlreadyExistsException;
import com.aldahirzamora.personal_manager_expense_backend.auth.repository.RoleRepository;
import com.aldahirzamora.personal_manager_expense_backend.auth.repository.UserRepository;
import com.aldahirzamora.personal_manager_expense_backend.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado tras autenticacion"));

        String token = jwtService.generateToken(user);
        return AuthResponse.of(token, user.getUsername(), jwtService.getExpirationMs());
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("El username ya esta en uso");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("El email ya esta en uso");
        }

        Role defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Rol por defecto 'USER' no encontrado"));

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(Set.of(defaultRole))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthResponse.of(token, user.getUsername(), jwtService.getExpirationMs());
    }
}
