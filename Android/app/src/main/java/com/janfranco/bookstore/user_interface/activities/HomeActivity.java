package com.janfranco.bookstore.user_interface.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.janfranco.bookstore.R;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
    }
}
