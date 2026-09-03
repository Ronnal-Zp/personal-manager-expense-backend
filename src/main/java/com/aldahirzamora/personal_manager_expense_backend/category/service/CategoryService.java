package com.aldahirzamora.personal_manager_expense_backend.category.service;

import com.aldahirzamora.personal_manager_expense_backend.category.dto.request.CreateCategoryRequest;
import com.aldahirzamora.personal_manager_expense_backend.category.dto.response.CategoryItem;
import com.aldahirzamora.personal_manager_expense_backend.category.dto.response.CategoryMapper;
import com.aldahirzamora.personal_manager_expense_backend.category.entity.Category;
import com.aldahirzamora.personal_manager_expense_backend.category.repository.CategoryRepository;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.ListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.MetaListResponse;
import com.aldahirzamora.personal_manager_expense_backend.core.dto.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor()
public class CategoryService {

    private static final Set<String> SORTABLE_FIELDS = Set.of("id", "name", "budget_Limit");
    private static final String DEFAULT_SORT_FIELD = "name";

    private final CategoryRepository categoryRepository;

    public ListResponse<CategoryItem> list(PageQuery query, Long userOwner) {
        Page<Category> categories = categoryRepository
                .findAllByUserOwner(userOwner, query.toPageable(SORTABLE_FIELDS, DEFAULT_SORT_FIELD));

        List<CategoryItem> items = categories.getContent().stream()
                .map(CategoryMapper::toItem)
                .toList();

        return new ListResponse<>(items, MetaListResponse.from(categories));
    }

    @Transactional
    public CategoryItem create(CreateCategoryRequest request, Long userOwner) {
        Category category = categoryRepository.save(CategoryMapper.toEntity(request, userOwner));

        return CategoryMapper.toItem(category);
    }

}
