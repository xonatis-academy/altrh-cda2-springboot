package fr.prody.prody.controllers;

import fr.prody.prody.domain.Category;
import fr.prody.prody.domain.Product;
import fr.prody.prody.dtos.CategoryRequestDto;
import fr.prody.prody.exceptions.CategoryExistsException;
import fr.prody.prody.exceptions.CategoryNotRemovableException;
import fr.prody.prody.services.CategoryService;
import fr.prody.prody.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @PostMapping("/api/v1/categories")
    public ResponseEntity<Category> create(@RequestBody CategoryRequestDto dto) throws CategoryExistsException {
        Category category = categoryService.add(dto.getName(), dto.getDescription());
        return ResponseEntity.status(201).body(category);
    }

    @PostMapping("/api/v1/categories/{categoryId}/products/{productId}/attach")
    public ResponseEntity<Category> attach(@PathVariable("categoryId") int categoryId, @PathVariable("productId") int productId) {
        Category category = categoryService.findById(categoryId);
        Product product = productService.findById(productId);
        category = categoryService.addProduct(category, product);
        return ResponseEntity.status(200).body(category);
    }

    @PutMapping("/api/v1/categories/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") int id, @RequestBody CategoryRequestDto dto) throws CategoryExistsException {
        Category category = categoryService.findById(id);
        category = categoryService.update(category, dto.getName(), dto.getDescription());
        return ResponseEntity.status(200).body(category);
    }

    @DeleteMapping("/api/v1/categories/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") int id) throws CategoryNotRemovableException {
        Category category = categoryService.findById(id);
        categoryService.remove(category);
        return ResponseEntity.status(204).body(null);
    }
}
