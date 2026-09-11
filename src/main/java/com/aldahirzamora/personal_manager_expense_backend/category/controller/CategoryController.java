package com.aldahirzamora.personal_manager_expense_backend.category.controller;

import com.aldahirzamora.personal_manager_expense_backend.auth.entity.User;
import com.aldahirzamora.personal_manager_expense_backend.category.dto.request.CreateCategoryRequest;
import com.aldahirzamora.personal_manager_expense_backend.category.dto.response.CategoryItem;
import com.aldahirzamora.personal_manager_expense_backend.category.service.CategoryService;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.ListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.PageQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/${api.version}/category")
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "Gestion de categorias de gastos del usuario autenticado")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Listar categorias", description = "Lista paginada de las categorias del usuario autenticado")
    public ResponseEntity<ListResponse<CategoryItem>> list(
            @Valid PageQuery query,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(categoryService.list(query, user.getId()));
    }

    @PostMapping
    @Operation(summary = "Crear categoria", description = "Crea una nueva categoria para el usuario autenticado")
    public ResponseEntity<CategoryItem> create(
            @Valid @RequestBody CreateCategoryRequest request,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request, user.getId()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoria", description = "Elimina fisicamente una categoria")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Id de la categoria") @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        categoryService.delete(id, user.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
