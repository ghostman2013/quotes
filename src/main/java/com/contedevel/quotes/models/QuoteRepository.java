package com.contedevel.quotes.models;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {
    List<Quote> findAllByUser(User user, Pageable pageable);
    List<Quote> findTop10ByOrderByLikesDescDislikesAsc();
    List<Quote> findTop10ByOrderByDislikesDescLikesAsc();
    Quote findFirstByOrderByUpdatedAtDesc();
}
