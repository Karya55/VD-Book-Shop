package com.janfranco.bookstore.entities;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
