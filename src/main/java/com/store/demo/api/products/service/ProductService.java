package com.store.demo.api.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.store.demo.api.products.entity.Product;
import com.store.demo.api.products.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    public Page<Product> findAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }
}
