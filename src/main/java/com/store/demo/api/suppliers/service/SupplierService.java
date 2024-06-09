package com.store.demo.api.suppliers.service;

import java.util.List;

import com.store.demo.api.suppliers.dto.SupplierDTO;

public interface SupplierService {
    public List<SupplierDTO> getAllSuppliers();

    SupplierDTO getSupplierById(long id);

    void saveSupplier(SupplierDTO supplierDTO);

    void updateSupplier(long id, SupplierDTO supplierDTO);

    void deleteSupplier(long id);
}
