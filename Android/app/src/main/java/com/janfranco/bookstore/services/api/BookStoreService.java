package com.janfranco.bookstore.services.api;

import com.janfranco.bookstore.entities.AccessToken;
import com.janfranco.bookstore.entities.Dtos.UserForRegisterDto;
import com.janfranco.bookstore.entities.Dtos.UserForLoginDto;
import com.janfranco.bookstore.entities.Result;
import com.janfranco.bookstore.helpers.RepositoryBase;
import com.janfranco.bookstore.helpers.ResultCallback;

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

    public void login(UserForLoginDto userLoginDto, ResultCallback<AccessToken> resultCallback) {
        Call<Result<AccessToken>> call = mBookStoreAPI.login(userLoginDto);
        mRepositoryBase.query(call, resultCallback);
    }

    public void register(UserForRegisterDto userRegisterDto, ResultCallback<AccessToken> resultCallback) {
        Call<Result<AccessToken>> call = mBookStoreAPI.register(userRegisterDto);
        mRepositoryBase.query(call, resultCallback);
    }

}
