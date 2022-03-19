package com.example.ourcw.controllers.Exceptions;


public class LoginOnceException extends Exception {
    private String userType;

    public LoginOnceException(String userType) {
        super();
        this.userType = userType;
    }


    public String getUserType() {
        return userType;
    }
}
