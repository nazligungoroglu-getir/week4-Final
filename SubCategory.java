package com.academy.model;

import java.util.ArrayList;
import java.util.List;

public class SubCategory {
    private String name;
    private List<Product> products;

    public SubCategory(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProducts(List<Product> products){
        this.products.addAll(products);
    }
}
