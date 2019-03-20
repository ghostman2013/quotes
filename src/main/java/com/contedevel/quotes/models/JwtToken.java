package com.contedevel.quotes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class JwtToken implements IType {

    private long id;
    private String email;
    private String name;
    private String token;
    @JsonProperty("created_at")
    private Date createdAt;

    public JwtToken(long id, String email, String name, String token) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.token = token;
        createdAt = new Date();
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getType() {
        return "token";
    }
}
