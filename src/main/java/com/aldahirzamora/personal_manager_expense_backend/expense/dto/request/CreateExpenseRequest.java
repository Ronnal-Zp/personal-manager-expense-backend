package com.aldahirzamora.personal_manager_expense_backend.expense.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CreateExpenseRequest {

    @NotBlank
    @Size(max = 255)
    private String title;

    private String description;

    private Date date;

    @NotNull
    @Positive
    private Long amount;

    @NotNull
    @Pattern(regexp = "^[+-]$", message = "debe ser '+' o '-'")
    private String sum_rest_sign;

    @NotNull
    private Long category_id;
}
