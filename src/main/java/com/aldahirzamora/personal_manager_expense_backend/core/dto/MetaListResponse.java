package com.aldahirzamora.personal_manager_expense_backend.core.dto;

import lombok.Data;

@Data
public class MetaListResponse {
    private int page;
    private int limit;
    private int totalItems;
    private int totalPages;
}
