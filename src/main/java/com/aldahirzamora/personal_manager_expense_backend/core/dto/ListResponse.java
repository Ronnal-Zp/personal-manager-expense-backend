package com.aldahirzamora.personal_manager_expense_backend.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListResponse<T> {
    private List<T> data;
    private MetaListResponse meta;
}
