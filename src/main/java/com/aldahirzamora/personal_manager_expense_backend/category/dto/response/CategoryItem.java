package com.aldahirzamora.personal_manager_expense_backend.category.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryItem {
    private Long id;
    private String name;
    private Long budget_Limit;
    private String icon;
    private String text_color;
    private String color;
    private Long user_owner;
}
