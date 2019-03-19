package com.contedevel.quotes.models.database;

import com.contedevel.quotes.models.database.entities.Quote;
import com.contedevel.quotes.models.database.entities.User;
import com.contedevel.quotes.models.database.entities.Vote;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VoteRepository extends PagingAndSortingRepository<Vote, Long> {
    void deleteByUserAndQuote(User user, Quote quote);
}
