package com.store.demo.api.suppliers.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.demo.api.products.entity.Product;
import com.store.demo.api.suppliers.entity.Supplier;
import com.store.demo.api.suppliers.repository.SupplierRepository;
import com.store.demo.api.suppliers.dto.SupplierDTO;
import com.store.demo.api.products.dto.ProductDTO;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierList.stream().map(this::convertToSupplierDTO).collect(Collectors.toList());
    }

    private SupplierDTO convertToSupplierDTO(Supplier supplier) {
        List<Product> products = supplier.getProducts();
        List<ProductDTO> dto = products.stream()
                .map(product -> new ProductDTO(product.getId(), product.getNombre(), product.getDescripcion(),
                        product.getPrecio()))
                .collect(Collectors.toList());

        return new SupplierDTO(supplier.getSupplierID(), supplier.getCompanyName(), dto);
    }

    @Override
    public SupplierDTO getSupplierById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSupplierById'");
    }

    @Override
    public void saveSupplier(SupplierDTO supplierDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveSupplier'");
    }

    @Override
    public void updateSupplier(long id, SupplierDTO supplierDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSupplier'");
    }

    @Override
    public void deleteSupplier(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSupplier'");
    }

}
