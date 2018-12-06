package com.cognizant.com.bookstore;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepository.list();
    }



}
