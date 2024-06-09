package com.store.demo.api.suppliers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.demo.api.suppliers.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
