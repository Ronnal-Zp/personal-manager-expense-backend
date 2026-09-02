package com.aldahirzamora.personal_manager_expense_backend.expense.service;

import com.aldahirzamora.personal_manager_expense_backend.core.dto.ListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.MetaListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.PageQuery;
import com.aldahirzamora.personal_manager_expense_backend.core.exception.ResourceNotFoundException;
import com.aldahirzamora.personal_manager_expense_backend.category.entity.Category;
import com.aldahirzamora.personal_manager_expense_backend.category.repository.CategoryRepository;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.request.CreateExpenseRequest;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.ExpenseItem;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.ExpenseMapper;
import com.aldahirzamora.personal_manager_expense_backend.expense.entity.Expense;
import com.aldahirzamora.personal_manager_expense_backend.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor()
public class ExpenseService {

    private static final Set<String> SORTABLE_FIELDS = Set.of("id", "title", "date", "amount");
    private static final String DEFAULT_SORT_FIELD = "date";

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ListResponse<ExpenseItem> list(PageQuery query, Long userOwner) {
        Page<Expense> expenses = expenseRepository
                .findAllByUserOwner(userOwner, query.toPageable(SORTABLE_FIELDS, DEFAULT_SORT_FIELD));

        List<ExpenseItem> items = expenses.getContent().stream()
                .map(ExpenseMapper::toItem)
                .toList();

        return new ListResponse<>(items, MetaListResponse.from(expenses));
    }


    @Transactional
    public ExpenseItem create(CreateExpenseRequest request, Long userOwner) {
        Category category = categoryRepository.findById(request.getCategory_id())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoria no encontrada: " + request.getCategory_id()));

        Expense expense = expenseRepository.save(ExpenseMapper.toEntity(request, category, userOwner));

        return ExpenseMapper.toItem(expense);
    }

}
