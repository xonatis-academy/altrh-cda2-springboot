package com.stocky.stoky.services.impl;

import com.stocky.stoky.domain.Product;
import com.stocky.stoky.repositories.ProductRepository;
import com.stocky.stoky.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product add(String name, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        return productRepository.save(product);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Product> list() {
        return productRepository.findAll();
    }

    @Override
    public Product decrement(Product product) {
        int newStock = product.getStock() - 1;
        if (newStock <= 0) {
            productRepository.delete(product);
            return null;
        }
        product.setStock(newStock);
        productRepository.save(product);
        return product;
    }
}
