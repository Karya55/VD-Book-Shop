package com.janfranco.bookstore.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("_id")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("role")
    private String role;

    @SerializedName("cart")
    private String cartId;

    @SerializedName("favorites")
    private List<Book> favorites;

    @SerializedName("reviews")
    private List<Review> reviews;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getRole() {
        return role;
    }

}
