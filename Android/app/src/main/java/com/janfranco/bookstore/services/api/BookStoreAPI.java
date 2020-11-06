package com.janfranco.bookstore.services.api;

import com.janfranco.bookstore.entities.AccessToken;
import com.janfranco.bookstore.entities.Book;
import com.janfranco.bookstore.entities.BookList;
import com.janfranco.bookstore.entities.Dtos.BookForAddDto;
import com.janfranco.bookstore.entities.Dtos.UserForLoginDto;
import com.janfranco.bookstore.entities.Dtos.UserForRegisterDto;
import com.janfranco.bookstore.entities.Result;
import com.janfranco.bookstore.entities.User;
import com.janfranco.bookstore.entities.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookStoreAPI {

    // AUTH
    @POST("auth/login")
    Call<Result<AccessToken>> login(@Body UserForLoginDto userLoginDto);

    @POST("auth/register")
    Call<Result<AccessToken>> register(@Body UserForRegisterDto userRegisterDto);

    // USER
    @GET("user/{user_id}")
    Call<Result<User>> getUser(@Header("Authorization") String accessToken,
                               @Path(value="user_id") String userId);

    @GET("user/all")
    Call<Result<UserList>> getAllUsers(@Header("Authorization") String accessToken);

    // BOOK
    @POST("book/add")
    Call<Result> addBook(@Header("Authorization") String accessToken, @Body BookForAddDto bookAddDto);

    @GET("book/{book_id}")
    Call<Result<Book>> getBook(@Header("Authorization") String accessToken,
                               @Path(value="book_id") String bookId);

    @GET("book/all")
    Call<Result<BookList>> getAllBooks(@Header("Authorization") String accessToken);

}
