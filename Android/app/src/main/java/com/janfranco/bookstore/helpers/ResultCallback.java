package com.janfranco.bookstore.helpers;

public interface ResultCallback<T> {

    void onSuccess(T data);
    void onFailure(String message);

}
