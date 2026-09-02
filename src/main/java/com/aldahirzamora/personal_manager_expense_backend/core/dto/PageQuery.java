package com.aldahirzamora.personal_manager_expense_backend.core.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Set;

@Data
public class PageQuery {

    public static final int MAX_LIMIT = 100;

    @Min(1)
    private int page = 1;

    @Min(1)
    @Max(MAX_LIMIT)
    private int limit = 10;

    private String sortBy;

    private Sort.Direction direction = Sort.Direction.DESC;

    public Pageable toPageable(Set<String> allowedSortFields, String defaultSortBy) {
        String field = (sortBy != null && allowedSortFields.contains(sortBy)) ? sortBy : defaultSortBy;
        return PageRequest.of(page - 1, limit, Sort.by(direction, field));
    }
}
