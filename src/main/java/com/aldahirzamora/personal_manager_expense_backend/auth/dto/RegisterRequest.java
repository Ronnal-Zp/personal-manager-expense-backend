package com.aldahirzamora.personal_manager_expense_backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "El username es obligatorio") String username,
        @NotBlank(message = "El email es obligatorio") @Email(message = "Email invalido") String email,
        @NotBlank(message = "El password es obligatorio") @Size(min = 8, message = "El password debe tener al menos 8 caracteres") String password
) {
}
