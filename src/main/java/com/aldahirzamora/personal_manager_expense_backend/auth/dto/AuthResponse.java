package com.aldahirzamora.personal_manager_expense_backend.auth.dto;

public record AuthResponse(
        String token,
        String tokenType,
        String username,
        long expiresInMs
) {
    public static AuthResponse of(String token, String username, long expiresInMs) {
        return new AuthResponse(token, "Bearer", username, expiresInMs);
    }
}
