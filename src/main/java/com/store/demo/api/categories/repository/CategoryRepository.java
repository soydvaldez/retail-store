package com.store.demo.api.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

import com.store.demo.api.categories.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT new com.store.demo.api.categories.entity.Category(c.id, c.nombre, c.descripcion) FROM Category c WHERE c.id = :categoriaId")
    Optional<Category> findCategoryDTOById(@Param("categoriaId") Long categoriaId);
}
