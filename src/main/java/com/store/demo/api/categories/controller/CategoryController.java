package com.store.demo.api.categories.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.store.demo.api.categories.dto.CategoryDTO;
import com.store.demo.api.categories.entity.Category;
import com.store.demo.api.categories.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> getCategoriesPaged(Pageable pageable) {
        Page<Category> page = categoryService.getCategories(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long categoryId) {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products")
    public ResponseEntity<Page<CategoryDTO>> getCategoriesWithProductsPaged(Pageable pageable) {
        Page<CategoryDTO> page = categoryService.getCategoriesWithProductsPaged(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<CategoryDTO> getCategoryByIdAndWithProducts(
            @PathVariable("id") Long categoryId,
            Pageable pageable) {
        return categoryService.getCategoryByIdAndWithProducts(categoryId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}