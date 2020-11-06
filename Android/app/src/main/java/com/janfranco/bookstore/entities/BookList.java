package com.janfranco.bookstore.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookList {

    @SerializedName("users")
    List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

}
