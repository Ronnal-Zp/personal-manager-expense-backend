package com.aldahirzamora.personal_manager_expense_backend.category.controller;

import com.aldahirzamora.personal_manager_expense_backend.auth.entity.User;
import com.aldahirzamora.personal_manager_expense_backend.category.dto.request.CreateCategoryRequest;
import com.aldahirzamora.personal_manager_expense_backend.category.dto.response.CategoryItem;
import com.aldahirzamora.personal_manager_expense_backend.category.service.CategoryService;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.ListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.PageQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${api.version}/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ListResponse<CategoryItem>> list(
            @Valid PageQuery query,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(categoryService.list(query, user.getId()));
    }

    @PostMapping
    public ResponseEntity<CategoryItem> create(
            @Valid @RequestBody CreateCategoryRequest request,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request, user.getId()));
    }

}
