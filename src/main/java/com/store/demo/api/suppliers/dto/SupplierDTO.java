package com.store.demo.api.suppliers.dto;

import java.util.List;

import com.store.demo.api.products.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private Long supplierID;
    private String companyName;
    private List<ProductDTO> products;
}
