package com.aldahirzamora.personal_manager_expense_backend.category.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateCategoryRequest {
    @Size(max = 255)
    private String name;

    @PositiveOrZero
    private Long budget_Limit;

    @Size(max = 25)
    private String icon;

    @Size(max = 25)
    private String text_color;

    @Size(max = 25)
    private String color;
}
