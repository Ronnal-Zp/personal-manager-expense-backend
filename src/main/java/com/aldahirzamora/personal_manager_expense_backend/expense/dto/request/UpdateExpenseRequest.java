package com.aldahirzamora.personal_manager_expense_backend.expense.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateExpenseRequest {
    @Size(max = 255)
    private String title;

    @Size(max = 255)
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @Positive
    private Long amount;

    @Pattern(regexp = "^[+-]$", message = "debe ser '+' o '-'")
    private String sum_rest_sign;

    @NotNull
    private Long category_id;

    @NotNull
    private Long user_id;
}
