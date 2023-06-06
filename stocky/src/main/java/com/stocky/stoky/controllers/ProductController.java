package com.stocky.stoky.controllers;

import com.stocky.stoky.domain.Product;
import com.stocky.stoky.dto.ProductDto;
import com.stocky.stoky.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/api/v1/products")
    public ResponseEntity<Product> create(@RequestBody ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setStock(dto.getStock());

        productService.add(product);

        return ResponseEntity.status(201).body(product);
    }

}
