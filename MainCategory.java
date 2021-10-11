package com.academy.model;

import java.util.ArrayList;
import java.util.List;

public class MainCategory {
    private String name;
    private List<SubCategory> subCategories;

    public MainCategory(String name) {
        this.name = name;
        this.subCategories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void addSubCategories(List<SubCategory> subCategories){
        this.subCategories.addAll(subCategories);
    }
}
