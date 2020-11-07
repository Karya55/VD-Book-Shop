package com.janfranco.bookstore.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Favorites {

    @SerializedName("favorites")
    List<Book> favorites;

    public List<Book> getFavorites() {
        return favorites;
    }

}
