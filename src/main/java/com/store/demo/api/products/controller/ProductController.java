package com.store.demo.api.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.demo.api.products.entity.Product;
import com.store.demo.api.products.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "products", description = "Operaciones relacionadas a la gestion e inventario de productos")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Operation(summary = "Obtener lista de productos", description = "Retorna una lista de productos del sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos encontrada"),
            // @ApiResponse(responseCode = "404", description = "Lista de productos NO encontrada"),
            // @ApiResponse(responseCode = "401", description = "Usuario No Autenticado"),
            // @ApiResponse(responseCode = "403", description = "Usuario No Autotizado"),
            // @ApiResponse(responseCode = "500", description = "Error interno en el servidor")
    })
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable inputPageable) {
        // Crear el objeto Pageable y settear los valores proporcionados:
        PageRequest pageable = PageRequest.of(
                inputPageable.getPageNumber(),
                inputPageable.getPageSize(),
                inputPageable.getSort());
        return ResponseEntity.ok(productService.findAll(pageable));
    }
}
