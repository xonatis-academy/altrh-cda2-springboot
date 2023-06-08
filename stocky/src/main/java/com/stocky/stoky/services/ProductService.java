package com.stocky.stoky.services;

import com.stocky.stoky.domain.Product;

public interface ProductService {

    Product add(String name, int stock);

    Product findById(int id);

    Iterable<Product> list();

    Product decrement(Product product);

}
