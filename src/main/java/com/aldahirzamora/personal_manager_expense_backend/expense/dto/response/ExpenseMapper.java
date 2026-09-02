package com.aldahirzamora.personal_manager_expense_backend.expense.dto.response;

import com.aldahirzamora.personal_manager_expense_backend.category.entity.Category;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.request.CreateExpenseRequest;
import com.aldahirzamora.personal_manager_expense_backend.expense.entity.Expense;

import java.util.Date;

public class ExpenseMapper {

    private ExpenseMapper() {
    }

    public static ExpenseItem toItem(Expense expense) {
        ExpenseItem item = new ExpenseItem();
        item.setId(expense.getId());
        item.setTitle(expense.getTitle());
        item.setDescription(expense.getDescription());
        item.setDate(expense.getDate());
        item.setAmount(expense.getAmount());
        item.setSum_rest_sign(expense.getSum_rest_sign());
        item.setCategory(expense.getCategory());
        item.setUser_owner(expense.getUser_owner());
        return item;
    }

    public static Expense toEntity(CreateExpenseRequest request, Category category, Long userOwner) {
        return Expense.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .date(request.getDate() != null ? request.getDate() : new Date())
                .amount(request.getAmount())
                .sum_rest_sign(request.getSum_rest_sign())
                .category(category)
                .user_owner(userOwner)
                .build();
    }
}
