package com.aldahirzamora.personal_manager_expense_backend.expense.dto.response;

public interface TotalExpenseByCategory {
    String getCategoryName();
    Long getCategoryId();
    Long getTotal();
    Long getBudgetLimit();
}
