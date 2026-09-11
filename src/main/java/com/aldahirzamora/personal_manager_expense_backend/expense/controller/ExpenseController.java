package com.aldahirzamora.personal_manager_expense_backend.expense.controller;

import com.aldahirzamora.personal_manager_expense_backend.core.dto.ListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.PageQuery;
import com.aldahirzamora.personal_manager_expense_backend.auth.entity.User;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.request.CreateExpenseRequest;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.request.UpdateExpenseRequest;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.ExpenseItem;
import com.aldahirzamora.personal_manager_expense_backend.expense.service.ExpenseService;
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
@RequestMapping("/api/${api.version}/expense")
@RequiredArgsConstructor
@Tag(name = "Gastos", description = "Gestion de gastos del usuario autenticado")
@SecurityRequirement(name = "bearerAuth")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    @Operation(summary = "Listar gastos", description = "Lista paginada de los gastos del usuario autenticado")
    public ResponseEntity<ListResponse<ExpenseItem>> list(
            @Valid PageQuery query,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(expenseService.list(query, user.getId()));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Listar gastos por categoria", description = "Lista paginada de los gastos del usuario autenticado filtrados por categoria")
    public ResponseEntity<ListResponse<ExpenseItem>> listByCategory(
            @Parameter(description = "Id de la categoria") @PathVariable Long categoryId,
            @Valid PageQuery query,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(expenseService.listByCategory(categoryId, query, user.getId()));
    }

    @GetMapping("/totalByCategory")
    @Operation(summary = "Obtener total de gastos por categoria", description = "Obtener total de gastos por categoria")
    public ResponseEntity<Object> listByCategory(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(expenseService.getTotalByCategory(user.getId()));
    }

    @PostMapping
    @Operation(summary = "Crear gasto", description = "Crea un nuevo gasto para el usuario autenticado")
    public ResponseEntity<ExpenseItem> create(
            @Valid @RequestBody CreateExpenseRequest request,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.create(request, user.getId()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar gasto", description = "Actualiza gasto")
    public ResponseEntity<ExpenseItem> update(
            @Parameter(description = "Id del gasto") @PathVariable Long id,
            @Valid @RequestBody UpdateExpenseRequest request,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.update(id,request,user.getId()));
    }

    @DeleteMapping("/all")
    @Operation(summary = "Eliminar todos los gastos", description = "Eliminar todos los gastos del usuario")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal User user
    ) {
        expenseService.deleteAll(user.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar gasto", description = "Eliminar gasto")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Id del gasto") @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
