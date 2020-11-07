package com.janfranco.bookstore.user_interface.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.janfranco.bookstore.R;
import com.janfranco.bookstore.adapters.FavoritesRecyclerAdapter;
import com.janfranco.bookstore.adapters.UserReviewsRecyclerAdapter;
import com.janfranco.bookstore.entities.Book;
import com.janfranco.bookstore.entities.Favorites;
import com.janfranco.bookstore.entities.Review;
import com.janfranco.bookstore.entities.ReviewList;
import com.janfranco.bookstore.entities.User;
import com.janfranco.bookstore.helpers.ResultCallback;
import com.janfranco.bookstore.helpers.UnknownTypeException;
import com.janfranco.bookstore.services.SharedPreferencesService;
import com.janfranco.bookstore.services.api.BookStoreService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.profile_avatar_image_view) ImageView avatarImageView;
    @BindView(R.id.profile_username_text) TextView usernameText;
    @BindView(R.id.profile_favorites_recycler_view) RecyclerView favoritesRecycler;
    @BindView(R.id.profile_reviews_recycler_view) RecyclerView reviewsRecycler;

    @BindString(R.string.shared_pref_file_key) String sharedPrefFile;
    @BindString(R.string.token_key) String tokenKey;
    @BindString(R.string.intent_user_id) String userIdKey;
    @BindString(R.string.api_service_avatar_url) String avatarBaseUrl;

    private BookStoreService mBookStoreService;
    private FavoritesRecyclerAdapter mFavoritesRecyclerAdapter;
    private UserReviewsRecyclerAdapter mUserReviewsRecyclerAdapter;
    private String accessToken;
    private List<Book> favoriteBooks;
    private List<Review> userReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setup();
    }

    private void setup() {
        ButterKnife.bind(this);

        favoritesRecycler.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));

        favoriteBooks = new ArrayList<>();
        mFavoritesRecyclerAdapter = new FavoritesRecyclerAdapter(favoriteBooks, this);
        favoritesRecycler.setAdapter(mFavoritesRecyclerAdapter);

        userReviews = new ArrayList<>();
        mUserReviewsRecyclerAdapter = new UserReviewsRecyclerAdapter(userReviews, this);
        reviewsRecycler.setAdapter(mUserReviewsRecyclerAdapter);

        mBookStoreService = BookStoreService.getInstance();
        SharedPreferencesService mSharedPreferencesService =
                new SharedPreferencesService(this, sharedPrefFile);

        try {
            accessToken = mSharedPreferencesService.readData(tokenKey, "");
        } catch (UnknownTypeException ignored) { }

        Intent intent = getIntent();
        String userId = intent.getStringExtra(userIdKey);

        getUser(userId);
        getFavorites(userId);
        getReviews(userId);
    }

    private void getUser(String userId) {
        mBookStoreService.getUserById(accessToken, userId, new ResultCallback<User>() {
            @Override
            public void onSuccess(User data) {
                Picasso.get().load(avatarBaseUrl + data.getAvatar()).into(avatarImageView);
                usernameText.setText(data.getName());
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getFavorites(String userId) {
        mBookStoreService.getFavoritesOfUser(accessToken, userId, new ResultCallback<Favorites>() {
            @Override
            public void onSuccess(Favorites data) {
                favoriteBooks = data.getFavorites();
                mFavoritesRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getReviews(String userId) {
        mBookStoreService.getReviewsOfUser(accessToken, userId, new ResultCallback<ReviewList>() {
            @Override
            public void onSuccess(ReviewList data) {
                userReviews = data.getReviews();
                mUserReviewsRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
