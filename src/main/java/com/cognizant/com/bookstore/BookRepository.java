package com.cognizant.com.bookstore;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository {

    final List<Book> books = new ArrayList<Book>();

    public void save(Book book) {
        books.add(book);

    }

    public List<Book> list(){
        return books;
    }

}
