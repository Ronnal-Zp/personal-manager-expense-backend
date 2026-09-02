package com.aldahirzamora.personal_manager_expense_backend.category.repository;

import com.aldahirzamora.personal_manager_expense_backend.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
