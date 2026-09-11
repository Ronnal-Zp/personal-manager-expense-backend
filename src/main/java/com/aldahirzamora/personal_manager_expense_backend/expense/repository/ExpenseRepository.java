package com.aldahirzamora.personal_manager_expense_backend.expense.repository;

import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.TotalExpenseByCategory;
import com.aldahirzamora.personal_manager_expense_backend.expense.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("select e from Expense e where e.user_owner = :userOwner and e.deleted_at is null")
    Page<Expense> findAllByUserOwner(@Param("userOwner") Long userOwner, Pageable pageable);

    @Query("select e from Expense e where e.category.id = :categoryId and e.user_owner = :userOwner")
    Page<Expense> findAllByCategoryIdAndUserOwner(@Param("categoryId") Long categoryId,
                                                  @Param("userOwner") Long userOwner,
                                                  Pageable pageable);

    @Query(" select \n" +
            " c.name as categoryName,\n" +
            " c.id as categoryId,\n" +
            " sum(e.amount) as total,\n" +
            " c.budget_Limit as budgetLimit\n" +
            " from Expense e\n" +
            " left join e.category c\n" +
            " where e.user_owner = :userOwner" +
            " group by c.id, c.name, c.budget_Limit"
    )
    List<TotalExpenseByCategory> getTotalByCategory(@Param("userOwner") Long userOwner);

    @Modifying
    @Query("delete from Expense e where e.user_owner = :userOwner")
    void deleteAllByUserOwner(@Param("userOwner") Long userOwner);
}
