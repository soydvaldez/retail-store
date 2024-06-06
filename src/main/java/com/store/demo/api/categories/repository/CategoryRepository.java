package com.store.demo.api.categories.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

import com.store.demo.api.categories.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT new Category(c.id, c.nombre, c.descripcion) FROM Category c")
    Page<Category> findAllCategories(PageRequest pageRequest);

    @Query("SELECT new Category(c.id, c.nombre, c.descripcion) FROM Category c WHERE c.id = :categoriaId")
    Optional<Category> findCategoryById(@Param("categoriaId") Long categoriaId);
}
