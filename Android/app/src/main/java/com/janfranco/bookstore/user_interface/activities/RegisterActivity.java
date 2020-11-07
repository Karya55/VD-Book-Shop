package com.janfranco.bookstore.user_interface.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.janfranco.bookstore.R;
import com.janfranco.bookstore.entities.AccessToken;
import com.janfranco.bookstore.entities.Dtos.UserForRegisterDto;
import com.janfranco.bookstore.helpers.RegularExpressionHelper;
import com.janfranco.bookstore.helpers.ResultCallback;
import com.janfranco.bookstore.helpers.TokenHelper;
import com.janfranco.bookstore.helpers.UnknownTypeException;
import com.janfranco.bookstore.services.SharedPreferencesService;
import com.janfranco.bookstore.services.api.BookStoreService;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_username_edit_text) EditText usernameEditText;
    @BindView(R.id.register_mail_edit_text) EditText mailEditText;
    @BindView(R.id.register_password_edit_text) EditText passwordEditText;

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
        setContentView(R.layout.activity_register);

        setup();
    }

    private void setup (){
        ButterKnife.bind(this);

        mBookStoreService = BookStoreService.getInstance();
        mSharedPreferencesService = new SharedPreferencesService(this, sharedPrefFile);
        mTokenHelper = new TokenHelper();
    }

    @OnClick(R.id.register_button) void registerButtonClicked() {
        UserForRegisterDto userForRegisterDto = getInputs();

        if (userForRegisterDto == null) {
            Toast.makeText(this, badInputMessage, Toast.LENGTH_LONG).show();
            return;
        }

        clearInputs();
        register(userForRegisterDto);
    }

    private UserForRegisterDto getInputs() {
        String mail = mailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String username = usernameEditText.toString().trim();

        RegularExpressionHelper regularExpressionHelper =
                new RegularExpressionHelper("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        if (regularExpressionHelper.isValid(mail) && !password.isEmpty() && !username.isEmpty())
            return new UserForRegisterDto(mail, password, username);
        else
            return null;
    }

    private void clearInputs() {
        mailEditText.setText("");
        passwordEditText.setText("");
        usernameEditText.setText("");
    }

    private void register(UserForRegisterDto userRegisterDto) {
        mBookStoreService.register(userRegisterDto, new ResultCallback<AccessToken>() {
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
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void navigateToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
