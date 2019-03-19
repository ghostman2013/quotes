package com.contedevel.quotes.models.database.entities;

import com.contedevel.quotes.models.IType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quotes")
public class Quote extends AuditModel implements IType {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String text;

    @NotNull
    @Column(nullable = false)
    private String author;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Formula("(SELECT COUNT(v.id) FROM votes v WHERE v.quote_id = id AND v.liked = true)")
    private long likes;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Formula("(SELECT COUNT(v.id) FROM votes v WHERE v.quote_id = id AND v.liked = false)")
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

    @Override
    public String getType() {
        return "quote";
    }
}
