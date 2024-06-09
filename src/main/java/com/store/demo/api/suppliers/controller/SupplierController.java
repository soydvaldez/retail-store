package com.store.demo.api.suppliers.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.demo.api.suppliers.service.SupplierServiceImpl;
import com.store.demo.api.suppliers.dto.SupplierDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/suppliers")
@ControllerAdvice
public class SupplierController {
    @Autowired
    private SupplierServiceImpl supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception for debugging purposes
        // logger.error("Unhandled exception occurred", e);
        return new ResponseEntity<>("Se produjo un error interno en el servidor, consultar los logs.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
