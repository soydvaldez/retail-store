package com.store.demo.api.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.demo.api.products.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
