package com.store.demo.api.categories.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.store.demo.api.categories.dto.CategoryDTO;
import com.store.demo.api.categories.entity.Category;
import com.store.demo.api.categories.repository.CategoryRepository;
import com.store.demo.api.products.dto.ProductDTO;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> getCategories(PageRequest pageRequest) {
        Page<Category> categoriesPage = categoryRepository.findAllCategories(pageRequest);
        categoriesPage.map(this::convertToDTO);
        return categoriesPage;
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findCategoryById(categoryId);
    }

    public Page<CategoryDTO> getCategoriesWithProductsPaged(PageRequest pageRequest) {
        Page<Category> categoriesPage = categoryRepository.findAll(pageRequest);
        return categoriesPage.map(this::convertToDTOWithProducts);
    }

    public Optional<CategoryDTO> getCategoryByIdAndWithProducts(Long categoryId) {
        Optional<Category> categoryWithProductsPaged = categoryRepository.findById(categoryId);
        return categoryWithProductsPaged.map(this::convertToDTOWithProducts);
    }

    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getNombre(), category.getDescripcion());
    }

    private CategoryDTO convertToDTOWithProducts(Category category) {
        List<ProductDTO> productDTOs = category.getProducts()
                .stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO(product.getId(), product.getNombre(),
                            product.getDescripcion(), product.getPrecio());
                    return productDTO;
                })
                .collect(Collectors.toList());
        return new CategoryDTO(category.getId(), category.getNombre(), category.getDescripcion(), productDTOs);
    }

}
