package fr.prody.prody.services;

import fr.prody.prody.domain.Category;
import fr.prody.prody.domain.Product;
import fr.prody.prody.exceptions.CategoryExistsException;
import fr.prody.prody.exceptions.CategoryNotRemovableException;

public interface CategoryService {

    Category add(String name, String description) throws CategoryExistsException;

    Category addProduct(Category category, Product product);

    Category findById(int id);

    Category update(Category category, String name, String description) throws CategoryExistsException;

    void remove(Category category) throws CategoryNotRemovableException;

}
