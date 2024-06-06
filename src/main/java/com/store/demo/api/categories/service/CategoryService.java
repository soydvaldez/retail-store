package com.store.demo.api.categories.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Optional;
import com.store.demo.api.categories.dto.CategoryDTO;
import com.store.demo.api.categories.entity.Category;

public interface CategoryService {
    Page<Category> getCategories(PageRequest pageRequest);

    Optional<Category> getCategoryById(Long categoryId);

    Page<CategoryDTO> getCategoriesWithProductsPaged(PageRequest pageRequest);

    Optional<CategoryDTO> getCategoryByIdAndWithProducts(Long categoryId);
}
