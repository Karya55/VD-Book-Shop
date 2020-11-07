package com.janfranco.bookstore.services.api;

import com.janfranco.bookstore.entities.AccessToken;
import com.janfranco.bookstore.entities.Book;
import com.janfranco.bookstore.entities.BookList;
import com.janfranco.bookstore.entities.Cart;
import com.janfranco.bookstore.entities.CategoryList;
import com.janfranco.bookstore.entities.Dtos.ProductDto;
import com.janfranco.bookstore.entities.Dtos.ReviewForAddDto;
import com.janfranco.bookstore.entities.Dtos.UserForLoginDto;
import com.janfranco.bookstore.entities.Dtos.UserForRegisterDto;
import com.janfranco.bookstore.entities.Favorites;
import com.janfranco.bookstore.entities.Result;
import com.janfranco.bookstore.entities.ReviewList;
import com.janfranco.bookstore.entities.User;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookStoreAPI {

    // AUTH
    @POST("auth/login")
    Call<Result<AccessToken>> login(@Body UserForLoginDto userLoginDto);

    @POST("auth/register")
    Call<Result<AccessToken>> register(@Body UserForRegisterDto userRegisterDto);

    // USER
    @GET("user/")
    Call<Result<User>> getUser(@Header("Authorization") String accessToken);

    @GET("user/{user_id}")
    Call<Result<User>> getUserById(@Header("Authorization") String accessToken,
                               @Path(value="user_id") String userId);

    @GET("user/favorites")
    Call<Result<Favorites>> getFavorites(@Header("Authorization") String accessToken);

    @GET("user/{user_id}/favorites")
    Call<Result<Favorites>> getFavoritesOfUser(@Header("Authorization") String accessToken,
                                              @Path(value="user_id") String userId);

    @GET("user/reviews")
    Call<Result<ReviewList>> getReviews(@Header("Authorization") String accessToken);

    @GET("user/{user_id}/reviews")
    Call<Result<ReviewList>> getReviewsOfUser(@Header("Authorization") String accessToken,
                                              @Path(value="user_id") String userId);

    @GET("user/add-favorite/{book_id}")
    Call<Result> addFavorite(@Header("Authorization") String accessToken,
                             @Path(value="book_id") String bookId);

    @PUT("user/update-avatar")
    Call<Result> updateAvatar(@Header("Authorization") String accessToken, @Part MultipartBody.Part avatar);

    // CATEGORY
    @GET("book-category/")
    Call<Result<CategoryList>> getCategories(@Header("Authorization") String accessToken);

    @GET("book-category/{category_id}")
    Call<Result<BookList>> getBooksByCategory(@Header("Authorization") String accessToken,
                                              @Path(value="category_id") String categoryId,
                                              @Query("sortBy") String sortBy,
                                              @Query("limit") String limit,
                                              @Query("page") String page);

    // BOOK
    @GET("book/{book_id}")
    Call<Result<Book>> getBook(@Header("Authorization") String accessToken,
                               @Path(value="book_id") String bookId);

    @GET("book/all")
    Call<Result<BookList>> getAllBooks(@Header("Authorization") String accessToken,
                                       @Query("sortBy") String sortBy,
                                       @Query("limit") String limit,
                                       @Query("page") String page);

    // CART
    @GET("cart/{cart_id}")
    Call<Result<Cart>> getCart(@Header("Authorization") String accessToken,
                               @Path(value="cart_id") String cartId);

    @POST("cart/add-product")
    Call<Result> addProductToCart(@Header("Authorization") String accessToken, @Body ProductDto productDto);

    @DELETE("cart/add-product")
    Call<Result> removeProductFromCart(@Header("Authorization") String accessToken, @Body ProductDto productDto);

    // REVIEW
    @GET("review/user-reviews")
    Call<Result<ReviewList>> getUserReviews(@Header("Authorization") String accessToken);

    @POST("review/add")
    Call<Result> addReview(@Header("Authorization") String accessToken, @Body ReviewForAddDto reviewForAddDto);

}
