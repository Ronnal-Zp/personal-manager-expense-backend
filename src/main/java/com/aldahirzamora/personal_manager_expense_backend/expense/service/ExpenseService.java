package com.aldahirzamora.personal_manager_expense_backend.expense.service;

import com.aldahirzamora.personal_manager_expense_backend.core.dto.ListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.MetaListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.PageQuery;
import com.aldahirzamora.personal_manager_expense_backend.core.exception.ResourceNotFoundException;
import com.aldahirzamora.personal_manager_expense_backend.category.entity.Category;
import com.aldahirzamora.personal_manager_expense_backend.category.repository.CategoryRepository;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.request.CreateExpenseRequest;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.request.UpdateExpenseRequest;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.ExpenseItem;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.ExpenseMapper;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.TotalExpenseByCategory;
import com.aldahirzamora.personal_manager_expense_backend.expense.entity.Expense;
import com.aldahirzamora.personal_manager_expense_backend.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    public ListResponse<ExpenseItem> listByCategory(Long categoryId, PageQuery query, Long userOwner) {
        categoryRepository.findByIdAndUserOwner(categoryId, userOwner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoria no encontrada: " + categoryId));

        Page<Expense> expenses = expenseRepository
                .findAllByCategoryIdAndUserOwner(categoryId, userOwner,
                        query.toPageable(SORTABLE_FIELDS, DEFAULT_SORT_FIELD));

        List<ExpenseItem> items = expenses.getContent().stream()
                .map(ExpenseMapper::toItem)
                .toList();

        return new ListResponse<>(items, MetaListResponse.from(expenses));
    }

    public ListResponse<TotalExpenseByCategory> getTotalByCategory(Long userOwner) {
        List<TotalExpenseByCategory> expenseList = expenseRepository.getTotalByCategory(userOwner);

        MetaListResponse meta = MetaListResponse.builder()
                .page(1)
                .limit(expenseList.size())
                .totalItems(expenseList.size())
                .totalPages(1)
                .build();

        return new ListResponse<>(expenseList, meta);
    }


    @Transactional
    public ExpenseItem create(CreateExpenseRequest request, Long userOwner) {
        Category category = categoryRepository.findById(request.getCategory_id())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoria no encontrada: " + request.getCategory_id()));

        Expense expense = expenseRepository.save(ExpenseMapper.toEntity(request, category, userOwner));

        return ExpenseMapper.toItem(expense);
    }

    @Transactional
    public ExpenseItem update(Long id, UpdateExpenseRequest request, Long userOwner) {
        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Gasto no encontrado: " + id)
        );

        Category category = categoryRepository.findByIdAndUserOwner(expense.getCategory().getId(), userOwner).orElseThrow(
                () -> new ResourceNotFoundException("Gasto no dispone de categoria: " + expense.getCategory().getId())
        );

        if(request.getTitle() != null) {
            expense.setTitle(request.getTitle());
        }
        if(request.getDescription() != null) {
            expense.setDescription(request.getDescription());
        }
        if(request.getDate() != null) {
            expense.setDate(request.getDate());
        }
        if(request.getAmount() != null) {
            expense.setAmount(request.getAmount());
        }
        if(request.getCategory_id() != null) {
            expense.setCategory(category);
        }

        expenseRepository.save(expense);
        return ExpenseMapper.toItem(expense);
    }

    @Transactional
    public void delete(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Gasto no encontrado: " + id)
        );
        expense.setDeleted_at(new Date());
        expenseRepository.save(expense);
    }

    @Transactional
    public void deleteAll(Long userOwner) {
        expenseRepository.deleteAllByUserOwner(userOwner);
    }
}
