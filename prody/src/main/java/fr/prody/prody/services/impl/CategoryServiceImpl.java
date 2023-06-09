package fr.prody.prody.services.impl;

import fr.prody.prody.domain.Category;
import fr.prody.prody.domain.Product;
import fr.prody.prody.exceptions.CategoryExistsException;
import fr.prody.prody.exceptions.CategoryNotRemovableException;
import fr.prody.prody.repositories.CategoryRepository;
import fr.prody.prody.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category add(String name, String description) throws CategoryExistsException {
        Category existingCategory = categoryRepository.findByName(name);
        if (existingCategory == null) {
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);
            categoryRepository.save(category);
            return category;
        }

        throw new CategoryExistsException();
    }

    @Override
    public Category addProduct(Category category, Product product) {

        List<Product> products = new ArrayList<>();
        for (Product existingProduct : category.getProducts()) {
            products.add(existingProduct);
        }
        products.add(product);
        category.setProducts(products);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category update(Category category, String name, String description) throws CategoryExistsException {
        Category existingCategory = categoryRepository.findByName(name);
        if (existingCategory != null && existingCategory.getId() != category.getId()) {
            throw new CategoryExistsException();
        }

        category.setName(name);
        category.setDescription(description);
        categoryRepository.save(category);
        return category;

    }

    @Override
    public void remove(Category category) throws CategoryNotRemovableException {
        if (category.getProducts() != null) {
            throw new CategoryNotRemovableException();
        }
        categoryRepository.delete(category);
    }


}
