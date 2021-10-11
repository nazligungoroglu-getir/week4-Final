package com.academy.model;

public class Product {
    private String name;
    private String description;
    private double price;
    private double reducedPrice;

    public Product(String name, String description, double price, double reducedPrice) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.reducedPrice = reducedPrice;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getReducedPrice() {
        return reducedPrice;
    }
}
