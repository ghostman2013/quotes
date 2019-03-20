package com.contedevel.quotes.models.database.entities;

import com.contedevel.quotes.models.IType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User extends AuditModel implements IType {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Valid
    @NotEmpty
    @Email
    @Column(nullable = false)
    private String email;

    @Valid
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Valid
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    @Column(nullable = false)
    private String password;

    @JsonProperty(value = "password_confirm", access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    @Transient
    private String passwordConfirm;

// TODO: Add photo support
//    @Lob
//    @Column(name="photo")
//    private byte[] photo;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Formula("(SELECT COUNT(q.id) FROM quotes q WHERE q.user_id = id)")
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

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public long getQuotes() {
        return quotes;
    }

    @Override
    public String getType() {
        return "user";
    }
}
