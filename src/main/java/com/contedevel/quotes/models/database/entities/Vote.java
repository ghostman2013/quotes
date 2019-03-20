package com.contedevel.quotes.models.database.entities;

import com.contedevel.quotes.models.IType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Vote extends AuditModel implements IType {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean liked;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name="quote_id", nullable=false)
    private Quote quote;

    public long getId() {
        return id;
    }

    public Vote setId(long id) {
        this.id = id;
        return this;
    }

    public boolean isLiked() {
        return liked;
    }

    public Vote setLiked(boolean liked) {
        this.liked = liked;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Vote setUser(User user) {
        this.user = user;
        return this;
    }

    public Quote getQuote() {
        return quote;
    }

    public Vote setQuote(Quote quote) {
        this.quote = quote;
        return this;
    }

    @Override
    public String getType() {
        return "vote";
    }
}
