package com.example.ss3.model;

public class AuthenticationResponse {
    private  final  String jwt;
    private String username;
    private String email;

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public AuthenticationResponse(String jwt, String username, String email) {
        this.jwt = jwt;
        this.username = username;
        this.email = email;
    }
}
