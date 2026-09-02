package com.aldahirzamora.personal_manager_expense_backend.expense.repository;

import com.aldahirzamora.personal_manager_expense_backend.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory_Id(Long category_id);

}
