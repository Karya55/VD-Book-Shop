package com.janfranco.bookstore.user_interface.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.janfranco.bookstore.R;
import com.janfranco.bookstore.entities.AccessToken;
import com.janfranco.bookstore.entities.Dtos.UserForLoginDto;
import com.janfranco.bookstore.helpers.RegularExpressionHelper;
import com.janfranco.bookstore.helpers.ResultCallback;
import com.janfranco.bookstore.helpers.TokenHelper;
import com.janfranco.bookstore.helpers.UnknownTypeException;
import com.janfranco.bookstore.services.SharedPreferencesService;
import com.janfranco.bookstore.services.api.BookStoreService;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class AuthActivity extends AppCompatActivity {

    @BindView(R.id.auth_mail_edit_text) EditText mailEditText;
    @BindView(R.id.auth_password_edit_text) EditText passwordEditText;

    @BindString(R.string.shared_pref_file_key) String sharedPrefFile;
    @BindString(R.string.token_key) String tokenKey;
    @BindString(R.string.expiration_key) String expirationKey;
    @BindString(R.string.auth_bad_input_error_message) String badInputMessage;

    private BookStoreService mBookStoreService;
    private SharedPreferencesService mSharedPreferencesService;
    private TokenHelper mTokenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        setup();
        checkTokenExpiration();
    }

    private void setup() {
        ButterKnife.bind(this);

        mBookStoreService = BookStoreService.getInstance();
        mSharedPreferencesService = new SharedPreferencesService(this, sharedPrefFile);
        mTokenHelper = new TokenHelper();
    }

    private void checkTokenExpiration() {
        try {
            long expirationDate = mSharedPreferencesService.readData(expirationKey, 0);
            if (expirationDate != 0 && !mTokenHelper.checkTokenExpiration(expirationDate))
                navigateToHomeActivity();
        } catch (UnknownTypeException ignored) { }
    }

    @OnClick(R.id.auth_login_button) void loginButtonClicked() {
        UserForLoginDto userForLoginDto = getInputs();

        if (userForLoginDto == null) {
            Toast.makeText(this, badInputMessage, Toast.LENGTH_LONG).show();
            return;
        }

        clearInputs();
        login(userForLoginDto);
    }

    @OnClick(R.id.auth_register_text) void registerTextClicked() {
        navigateToRegisterActivity();
    }

    private UserForLoginDto getInputs() {
        String mail = mailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        RegularExpressionHelper regularExpressionHelper =
                new RegularExpressionHelper("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        if (regularExpressionHelper.isValid(mail) && !password.isEmpty())
            return new UserForLoginDto(mail, password);
        else
            return null;
    }

    private void clearInputs() {
        mailEditText.setText("");
        passwordEditText.setText("");
    }

    private void login(UserForLoginDto userForLoginDto) {
        mBookStoreService.login(userForLoginDto, new ResultCallback<AccessToken>() {
            @Override
            public void onSuccess(AccessToken data) {
                String token = data.getToken();
                int expiration = data.getExpiration();

                long expirationDate = mTokenHelper.getExpirationDateInMilliseconds(expiration);

                try {
                    mSharedPreferencesService.writeData(tokenKey, token);
                    mSharedPreferencesService.writeData(expirationKey, expirationDate);
                } catch (UnknownTypeException ignored) { }

                navigateToHomeActivity();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(AuthActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void navigateToRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void navigateToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
