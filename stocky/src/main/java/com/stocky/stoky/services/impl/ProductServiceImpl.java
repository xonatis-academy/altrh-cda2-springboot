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
    public Product add(Product product) {
        return productRepository.save(product);
    }

}
