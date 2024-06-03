package com.store.demo.api.categories.dto;

import java.util.List;

import com.store.demo.api.products.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<ProductDTO> products;

    public CategoryDTO(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
