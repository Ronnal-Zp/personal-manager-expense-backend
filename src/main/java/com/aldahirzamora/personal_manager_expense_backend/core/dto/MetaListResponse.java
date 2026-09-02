package com.aldahirzamora.personal_manager_expense_backend.core.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class MetaListResponse {
    private int page;
    private int limit;
    private long totalItems;
    private int totalPages;

    /** Construye el meta a partir de una pagina de Spring Data (page vuelve a base 1). */
    public static MetaListResponse from(Page<?> page) {
        return MetaListResponse.builder()
                .page(page.getNumber() + 1)
                .limit(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
