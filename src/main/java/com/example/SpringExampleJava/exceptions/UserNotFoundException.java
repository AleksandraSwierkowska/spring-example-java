package com.example.SpringExampleJava.exceptions;

public class UserNotFoundException extends Exception {

    private String email;

    public UserNotFoundException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
