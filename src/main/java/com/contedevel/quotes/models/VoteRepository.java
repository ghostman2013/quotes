package com.contedevel.quotes.models;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VoteRepository extends PagingAndSortingRepository<Vote, Long> {
    List<Vote> findAllByQuoteId(Quote quote, Pageable pageable);
    int getCountByQuote(Quote quote);
}
