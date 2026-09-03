package com.aldahirzamora.personal_manager_expense_backend.category.dto.response;

import com.aldahirzamora.personal_manager_expense_backend.category.dto.request.CreateCategoryRequest;
import com.aldahirzamora.personal_manager_expense_backend.category.entity.Category;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryItem toItem(Category category) {
        return CategoryItem.builder()
                .id(category.getId())
                .name(category.getName())
                .budget_Limit(category.getBudget_Limit())
                .icon(category.getIcon())
                .text_color(category.getText_color())
                .color(category.getColor())
                .user_owner(category.getUser_owner())
                .build();
    }

    public static Category toEntity(CreateCategoryRequest request, Long userOwner) {
        return Category.builder()
                .name(request.getName())
                .budget_Limit(request.getBudget_Limit())
                .icon(request.getIcon())
                .text_color(request.getText_color())
                .color(request.getColor())
                .user_owner(userOwner)
                .build();
    }
}
