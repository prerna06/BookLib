package com.acc.bookservice.service;

import com.acc.bookservice.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * Service layer methods exposed for Books
 */
public interface BookService {

    List<Book> getBooks();

    Optional<Book> getBookById(String isbnNumber);

    List<Book> searchBooks(String isbnNumber, String title, String author);
}
