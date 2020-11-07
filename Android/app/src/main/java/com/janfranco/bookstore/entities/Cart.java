package com.janfranco.bookstore.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {

    @SerializedName("products")
    private List<Book> products;

    @SerializedName("totalPrice")
    private float totalPrice;

    public List<Book> getProducts() {
        return products;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

}
