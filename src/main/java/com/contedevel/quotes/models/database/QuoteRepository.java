package com.contedevel.quotes.models.database;

import com.contedevel.quotes.models.database.entities.Quote;
import com.contedevel.quotes.models.database.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {
    Page<Quote> findAllByUser(User user, Pageable pageable);
    Page<Quote> findAllByOrderByUpdatedAtDesc(Pageable pageable);
    List<Quote> findTop10ByOrderByLikesDescDislikesAsc();
    List<Quote> findTop10ByOrderByDislikesDescLikesAsc();
    Optional<Quote> findFirstByOrderByUpdatedAtDesc();
}
