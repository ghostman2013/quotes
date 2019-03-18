package com.contedevel.quotes.models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Email
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Transient
    private String passwordConfirm;

    @Lob
    @Column(name="photo")
    private byte[] photo;

    @Formula("(SELECT COUNT(q) FROM quotes q WHERE q.user_id = id)")
    private long quotes;

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public User setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
        return this;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public User setPhoto(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public long getQuotes() {
        return quotes;
    }
}
