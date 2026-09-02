package com.aldahirzamora.personal_manager_expense_backend.expense.service;

import com.aldahirzamora.personal_manager_expense_backend.core.dto.ListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.MetaListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.PageQuery;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.ExpenseItem;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.ExpenseMapper;
import com.aldahirzamora.personal_manager_expense_backend.expense.entity.Expense;
import com.aldahirzamora.personal_manager_expense_backend.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor()
public class ExpenseService {

    private static final Set<String> SORTABLE_FIELDS = Set.of("id", "title", "date", "amount");
    private static final String DEFAULT_SORT_FIELD = "date";

    private final ExpenseRepository expenseRepository;

    public ListResponse<ExpenseItem> list(PageQuery query) {
        Page<Expense> expenses = expenseRepository
                .findAll(query.toPageable(SORTABLE_FIELDS, DEFAULT_SORT_FIELD));

        List<ExpenseItem> items = expenses.getContent().stream()
                .map(ExpenseMapper::toItem)
                .toList();

        return new ListResponse<>(items, MetaListResponse.from(expenses));
    }

}
