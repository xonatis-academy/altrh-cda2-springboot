package fr.prody.prody.controllers;

import fr.prody.prody.domain.Product;
import fr.prody.prody.dtos.ProductRequestDto;
import fr.prody.prody.dtos.ProductResponseDto;
import fr.prody.prody.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/api/v1/products")
    public ResponseEntity<Product> create(@RequestBody ProductRequestDto dto) {
        Product product = productService.add(dto.getName(), dto.getStock());
        return ResponseEntity.status(201).body(product);
    }

    @GetMapping("/api/v1/products")
    public ResponseEntity<Iterable<Product>> list() {
        Iterable<Product> products = productService.list();
        return ResponseEntity.status(200).body(products);
    }

    // 1. Modifie le produit
    // PUT /api/v1/products/{id}
    // 2. Modifie le stock du produit
    // PUT /api/v1/products/{id}/stock
    // 3. On décrémente le stock du produit
    // POST /api/v1/products/{id}/decrement

    @PostMapping("/api/v1/products/{id}/decrement")
    public ResponseEntity<ProductResponseDto> decrement(@PathVariable("id") int id) {
        Product product = productService.findById(id);
        product = productService.decrement(product);

        ProductResponseDto dto = new ProductResponseDto();
        dto.setStock(product.getStock());

        return ResponseEntity.status(200).body(dto);
    }

}
