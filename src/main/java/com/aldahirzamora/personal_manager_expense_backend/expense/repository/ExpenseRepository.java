package com.aldahirzamora.personal_manager_expense_backend.expense.repository;

import com.aldahirzamora.personal_manager_expense_backend.expense.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("select e from Expense e where e.user_owner = :userOwner")
    Page<Expense> findAllByUserOwner(@Param("userOwner") Long userOwner, Pageable pageable);

    @Query("select e from Expense e where e.category.id = :categoryId and e.user_owner = :userOwner")
    Page<Expense> findAllByCategoryIdAndUserOwner(@Param("categoryId") Long categoryId,
                                                  @Param("userOwner") Long userOwner,
                                                  Pageable pageable);
}
