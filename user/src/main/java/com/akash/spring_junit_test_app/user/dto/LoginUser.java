package com.akash.spring_junit_test_app.user.dto;

public class LoginUser {
    private String username;
    private String password;

    public LoginUser() {

    }

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                " username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
