package com.contedevel.quotes.models;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Vote extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean liked;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

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
}
