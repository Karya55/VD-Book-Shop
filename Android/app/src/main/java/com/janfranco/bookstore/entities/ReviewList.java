package com.janfranco.bookstore.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewList {

    @SerializedName("reviews")
    List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

}
