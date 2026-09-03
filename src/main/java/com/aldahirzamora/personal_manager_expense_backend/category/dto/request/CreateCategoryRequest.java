package com.aldahirzamora.personal_manager_expense_backend.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryRequest {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    @PositiveOrZero
    private Long budget_Limit;

    private String icon;

    private String text_color;

    private String color;
}
