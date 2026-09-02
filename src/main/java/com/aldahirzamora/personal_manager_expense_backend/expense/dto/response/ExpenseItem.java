package com.aldahirzamora.personal_manager_expense_backend.expense.dto.response;

import com.aldahirzamora.personal_manager_expense_backend.category.entity.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ExpenseItem {
    private Long id;
    private String title;
    private String description;
    private Date date;
    private Long amount;
    private String sum_rest_sign;
    private Category category;
    private Long user_owner;
}
