package com.aldahirzamora.personal_manager_expense_backend.expense.controller;

import com.aldahirzamora.personal_manager_expense_backend.core.dto.ListResponse;
import com.aldahirzamora.personal_manager_expense_backend.expense.dto.response.ExpenseItem;
import com.aldahirzamora.personal_manager_expense_backend.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${api.version}/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<ListResponse<ExpenseItem>> list() {
        return ResponseEntity.ok(expenseService.list());
    }

}
