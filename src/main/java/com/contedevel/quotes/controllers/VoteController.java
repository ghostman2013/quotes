package com.contedevel.quotes.controllers;

import com.contedevel.quotes.exceptions.QuoteNotFoundException;
import com.contedevel.quotes.models.database.QuoteRepository;
import com.contedevel.quotes.models.database.VoteRepository;
import com.contedevel.quotes.models.database.entities.Quote;
import com.contedevel.quotes.models.database.entities.User;
import com.contedevel.quotes.models.database.entities.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quotes")
public class VoteController extends BaseController {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private VoteRepository voteRepository;

    @PutMapping("{quote_id}/votes/vote")
    @ResponseStatus(HttpStatus.CREATED)
    public Vote update(@PathVariable("quote_id") long quoteId, @RequestBody boolean liked)
            throws QuoteNotFoundException {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new QuoteNotFoundException(quoteId));
        User user = getCurrentUser();

        return voteRepository.save(new Vote()
                .setQuote(quote)
                .setUser(user)
                .setLiked(liked));
    }

    @DeleteMapping("{quote_id}/votes/vote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("quote_id") long quoteId) throws QuoteNotFoundException {
        User user = getCurrentUser();
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new QuoteNotFoundException(quoteId));
        voteRepository.deleteByUserAndQuote(user, quote);
    }
}
