package com.janfranco.bookstore.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryList {

    @SerializedName("categories")
    List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

}
