package com.aldahirzamora.personal_manager_expense_backend.category.repository;

import com.aldahirzamora.personal_manager_expense_backend.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.user_owner = :userOwner")
    Page<Category> findAllByUserOwner(@Param("userOwner") Long userOwner, Pageable pageable);
}
