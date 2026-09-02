package com.aldahirzamora.personal_manager_expense_backend.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor()
public class ErrorResponse {
    private Integer status;
    private String title;
    private String detail;
}
