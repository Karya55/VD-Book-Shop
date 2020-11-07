package com.janfranco.bookstore.entities;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("star")
    private float star;

    @SerializedName("review")
    private String review;

    @SerializedName("book")
    private Book book;

    @SerializedName("user")
    private User user;

    @SerializedName("_id")
    private String id;

    public float getStar() {
        return star;
    }

    public String getReview() {
        return review;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return id;
    }

}
