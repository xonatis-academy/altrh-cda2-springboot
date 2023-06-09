package fr.prody.prody.domain;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Category {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @ManyToMany
    private Collection<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public int getStock() {
        if (products == null) {
            return 0;
        }

        int stock = 0;
        for(Product product : products) {
            stock += product.getStock();
        }
        return stock;
    }
}
