package com.janfranco.bookstore.services.api;

import com.janfranco.bookstore.entities.AccessToken;
import com.janfranco.bookstore.entities.Book;
import com.janfranco.bookstore.entities.BookList;
import com.janfranco.bookstore.entities.Cart;
import com.janfranco.bookstore.entities.CategoryList;
import com.janfranco.bookstore.entities.Dtos.ProductDto;
import com.janfranco.bookstore.entities.Dtos.ReviewForAddDto;
import com.janfranco.bookstore.entities.Dtos.UserForRegisterDto;
import com.janfranco.bookstore.entities.Dtos.UserForLoginDto;
import com.janfranco.bookstore.entities.Favorites;
import com.janfranco.bookstore.entities.Result;
import com.janfranco.bookstore.entities.ReviewList;
import com.janfranco.bookstore.entities.User;
import com.janfranco.bookstore.helpers.RepositoryBase;
import com.janfranco.bookstore.helpers.ResultCallback;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookStoreService {

    private static BookStoreService instance;

    private final BookStoreAPI mBookStoreAPI;
    private final RepositoryBase mRepositoryBase;

    private BookStoreService() {
        String baseUrl = "http://37.148.209.192:3003/api/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        mBookStoreAPI = retrofit.create(BookStoreAPI.class);
        mRepositoryBase = new RepositoryBase();
    }

    public static BookStoreService getInstance() {
        if (instance == null)
            instance = new BookStoreService();

        return instance;
    }

    // AUTH
    public void login(UserForLoginDto userLoginDto, ResultCallback<AccessToken> resultCallback) {
        Call<Result<AccessToken>> call = mBookStoreAPI.login(userLoginDto);
        mRepositoryBase.query(call, resultCallback);
    }

    public void register(UserForRegisterDto userRegisterDto, ResultCallback<AccessToken> resultCallback) {
        Call<Result<AccessToken>> call = mBookStoreAPI.register(userRegisterDto);
        mRepositoryBase.query(call, resultCallback);
    }

    // USER
    public void getUser(String accessToken, ResultCallback<User> resultCallback) {
        Call<Result<User>> call = mBookStoreAPI.getUser(accessToken);
        mRepositoryBase.query(call, resultCallback);
    }

    public void getUserById(String accessToken, String userId, ResultCallback<User> resultCallback) {
        Call<Result<User>> call = mBookStoreAPI.getUserById(accessToken, userId);
        mRepositoryBase.query(call, resultCallback);
    }

    public void getFavorites(String accessToken, ResultCallback<Favorites> resultCallback) {
        Call<Result<Favorites>> call = mBookStoreAPI.getFavorites(accessToken);
        mRepositoryBase.query(call, resultCallback);
    }

    public void getFavoritesOfUser(String accessToken, String userId, ResultCallback<Favorites> resultCallback) {
        Call<Result<Favorites>> call = mBookStoreAPI.getFavoritesOfUser(accessToken, userId);
        mRepositoryBase.query(call, resultCallback);
    }

    public void getReviews(String accessToken, ResultCallback<ReviewList> resultCallback) {
        Call<Result<ReviewList>> call = mBookStoreAPI.getReviews(accessToken);
        mRepositoryBase.query(call, resultCallback);
    }

    public void getReviewsOfUser(String accessToken, String userId, ResultCallback<ReviewList> resultCallback) {
        Call<Result<ReviewList>> call = mBookStoreAPI.getReviewsOfUser(accessToken, userId);
        mRepositoryBase.query(call, resultCallback);
    }

    public void addFavorite(String accessToken, String bookId, ResultCallback<Result> resultCallback) {
        Call<Result> call = mBookStoreAPI.addFavorite(accessToken, bookId);
        mRepositoryBase.noDataQuery(call, resultCallback);
    }

    public void updateAvatar(String accessToken, String filePath, ResultCallback<Result> resultCallback) {
        File file = new File(filePath);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);

        Call<Result> call = mBookStoreAPI.updateAvatar(accessToken, body);
        mRepositoryBase.noDataQuery(call, resultCallback);
    }

    // CATEGORY
    public void getAllCategories(String accessToken, ResultCallback<CategoryList> resultCallback) {
        Call<Result<CategoryList>> call = mBookStoreAPI.getCategories(accessToken);
        mRepositoryBase.query(call, resultCallback);
    }

    /**
     * @param page Send 0 if you want to be default
     * @param limit Send 0 if you want to be default
     * @param sortBy Send empty string if you want to be default or one of these:
     *               "most-reviewed", "highest-price", "lowest-price"
     */
    public void getBooksByCategory(String accessToken, String categoryId, int page, int limit,
                                   String sortBy, ResultCallback<BookList> resultCallback) {
        Call<Result<BookList>> call = mBookStoreAPI.getBooksByCategory(accessToken, categoryId,
                sortBy, limit != 0 ? String.valueOf(limit) : "", page != 0 ? String.valueOf(page) : "");
        mRepositoryBase.query(call, resultCallback);
    }

    // BOOK
    public void getBook(String accessToken, String bookId, ResultCallback<Book> resultCallback) {
        Call<Result<Book>> call = mBookStoreAPI.getBook(accessToken, bookId);
        mRepositoryBase.query(call, resultCallback);
    }

    /**
     * @param page Send 0 if you want to be default
     * @param limit Send 0 if you want to be default
     * @param sortBy Send empty string if you want to be default or one of these:
     *               "most-reviewed", "highest-price", "lowest-price"
     */
    public void getAllBooks(String accessToken, int page, int limit,
                            String sortBy, ResultCallback<BookList> resultCallback) {
        Call<Result<BookList>> call = mBookStoreAPI.getAllBooks(accessToken, sortBy,
                limit != 0 ? String.valueOf(limit) : "", page != 0 ? String.valueOf(page) : "");
        mRepositoryBase.query(call, resultCallback);
    }

    // CART
    public void getCart(String accessToken, String cartId, ResultCallback<Cart> resultCallback) {
        Call<Result<Cart>> call = mBookStoreAPI.getCart(accessToken, cartId);
        mRepositoryBase.query(call, resultCallback);
    }

    public void addProductToCart(String accessToken, ProductDto productDto, ResultCallback resultCallback) {
        Call<Result> call = mBookStoreAPI.addProductToCart(accessToken, productDto);
        mRepositoryBase.noDataQuery(call, resultCallback);
    }

    public void removeProductFromCart(String accessToken, ProductDto productDto, ResultCallback resultCallback) {
        Call<Result> call = mBookStoreAPI.removeProductFromCart(accessToken, productDto);
        mRepositoryBase.noDataQuery(call, resultCallback);
    }

    // REVIEW
    public void addReview(String accessToken, ReviewForAddDto reviewForAddDto, ResultCallback resultCallback) {
        Call<Result> call = mBookStoreAPI.addReview(accessToken, reviewForAddDto);
        mRepositoryBase.noDataQuery(call, resultCallback);
    }

    public void getUserReviews(String accessToken, ResultCallback<ReviewList> resultCallback) {
        Call<Result<ReviewList>> call = mBookStoreAPI.getUserReviews(accessToken);
        mRepositoryBase.query(call, resultCallback);
    }

}
