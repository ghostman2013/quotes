package com.contedevel.quotes.controllers;

import com.contedevel.quotes.exceptions.NoAvailableQuotesException;
import com.contedevel.quotes.exceptions.QuoteNotFoundException;
import com.contedevel.quotes.models.database.entities.Quote;
import com.contedevel.quotes.models.database.QuoteRepository;
import com.contedevel.quotes.models.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuoteController extends BaseController {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private QuoteRepository repository;

    @GetMapping("/{id}")
    public Quote get(@PathVariable long id) throws QuoteNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));
    }

    @GetMapping("/last")
    public Page<Quote> getLast(@RequestParam(value = "page", defaultValue = "0") int page) {
        return repository.findAllByOrderByUpdatedAtDesc(PageRequest.of(page, PAGE_SIZE));
    }

    @GetMapping
    public Page<Quote> getAll(@RequestParam(value = "page", defaultValue = "0") int page) {
        return repository.findAllByOrderByUpdatedAtDesc(PageRequest.of(page, PAGE_SIZE));
    }

    @GetMapping("/mine")
    public Page<Quote> getMine(@RequestParam(value = "page", defaultValue = "0") int page) {
        User user = getCurrentUser();

        return repository.findAllByUserOrderByUpdatedAtDesc(user, PageRequest.of(page, PAGE_SIZE));
    }

    @GetMapping("/top10")
    public List<Quote> getTop10() {
        return repository.findTop10ByOrderByLikesDescDislikesAscUpdatedAtDesc();
    }

    @GetMapping("/flop10")
    public List<Quote> getFlop10() {
        return repository.findTop10ByOrderByDislikesDescLikesAscUpdatedAtDesc();
    }

    @GetMapping("/random")
    public Quote getRandom() throws NoAvailableQuotesException {
        long count = repository.count();

        if (count == 0) {
            throw new NoAvailableQuotesException();
        }

        Random random = new Random();
        int index = random.nextInt((int)count);
        Page<Quote> page = repository.findAll(PageRequest.of(index, 1));

        if (page.isEmpty()) {
            throw new NoAvailableQuotesException();
        }

        return page.getContent().get(0);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quote create(@Valid @RequestBody Quote quote) {
        User user = getCurrentUser();
        quote.setUser(user);

        return repository.save(quote);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }
}
