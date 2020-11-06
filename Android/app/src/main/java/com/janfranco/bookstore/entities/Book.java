package com.janfranco.bookstore.entities;

import com.google.gson.annotations.SerializedName;

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

}
