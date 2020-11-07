package com.janfranco.bookstore.user_interface.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.janfranco.bookstore.R;
import com.janfranco.bookstore.helpers.UnknownTypeException;
import com.janfranco.bookstore.services.SharedPreferencesService;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class SplashActivity extends AppCompatActivity {

    @BindString(R.string.shared_pref_file_key)
    String sharedPrefFile;

    @BindString(R.string.splash_first_time_key)
    String firstTimeKey;

    private SharedPreferencesService mSharedPreferencesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setup();
        checkFirstTime();
    }

    private void setup() {
        ButterKnife.bind(this);
        mSharedPreferencesService = new SharedPreferencesService(this, sharedPrefFile);
    }

    private void checkFirstTime() {
        boolean firstTime = false;

        try {
            firstTime = mSharedPreferencesService.readData(firstTimeKey, false);
        } catch (UnknownTypeException ignored) { }

        if (!firstTime)
            navigateToAuthActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            mSharedPreferencesService.writeData(firstTimeKey, false);
        } catch (UnknownTypeException ignored) { }
    }

    @OnClick(R.id.splash_button) void nextButtonClicked() {
        navigateToAuthActivity();
    }

    private void navigateToAuthActivity() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

}
