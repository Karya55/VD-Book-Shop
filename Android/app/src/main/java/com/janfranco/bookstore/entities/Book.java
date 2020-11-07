package com.janfranco.bookstore.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Book {

    @SerializedName("_id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("author")
    private String author;

    @SerializedName("cover")
    private String cover;

    @SerializedName("pageCount")
    private int pageCount;

    @SerializedName("price")
    private int price;

    @SerializedName("averageStar")
    private int averageStar;

    @SerializedName("category")
    List<Category> categoryList;

    @SerializedName("reviews")
    List<Review> reviews;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCover() {
        return cover;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPrice() {
        return price;
    }

    public int getAverageStar() {
        return averageStar;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

}
