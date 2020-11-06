package com.janfranco.bookstore.entities.Dtos;

public class UserForLoginDto {

    private final String email;
    private final String password;

    public UserForLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
