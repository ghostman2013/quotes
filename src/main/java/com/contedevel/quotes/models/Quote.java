package com.contedevel.quotes.models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quotes")
public class Quote extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String text;

    @NotNull
    @Column(nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Formula("(SELECT COUNT(v) FROM votes v WHERE v.quote_id = id AND v.like = true)")
    private long likes;

    @Formula("(SELECT COUNT(v) FROM votes v WHERE v.quote_id = id AND v.like = false)")
    private long dislikes;

    public long getId() {
        return id;
    }

    public Quote setId(long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Quote setText(String text) {
        this.text = text;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Quote setAuthor(String author) {
        this.author = author;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Quote setUser(User user) {
        this.user = user;
        return this;
    }

    public long getLikes() {
        return likes;
    }

    public long getDislikes() {
        return dislikes;
    }
}
