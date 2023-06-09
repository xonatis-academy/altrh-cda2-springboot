package fr.prody.prody.services;

import fr.prody.prody.domain.Product;

public interface ProductService {

    Product add(String name, int stock);

    Product findById(int id);

    Iterable<Product> list();

    Product decrement(Product product);

}
