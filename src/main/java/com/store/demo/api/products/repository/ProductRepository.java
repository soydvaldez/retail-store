package com.store.demo.api.products.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.store.demo.api.products.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
