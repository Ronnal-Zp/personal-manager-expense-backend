package com.aldahirzamora.personal_manager_expense_backend.expense.service;

import com.aldahirzamora.personal_manager_expense_backend.core.dto.ListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.MetaListResponse;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.ExpenseItem;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.ExpenseMapper;
import com.aldahirzamora.personal_manager_expense_backend.expense.entity.Expense;
import com.aldahirzamora.personal_manager_expense_backend.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ListResponse<ExpenseItem> list() {
        List<Expense> expenses = expenseRepository.findAll();

        List<ExpenseItem> items = expenses.stream()
                .map(ExpenseMapper::toItem)
                .toList();

        MetaListResponse meta = new MetaListResponse();
        meta.setPage(1);
        meta.setLimit(items.size());
        meta.setTotalItems(items.size());
        meta.setTotalPages(1);

        return new ListResponse<>(items, meta);
    }

}
