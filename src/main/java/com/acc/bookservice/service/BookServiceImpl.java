package com.acc.bookservice.service;

import com.acc.bookservice.dao.BookDao;
import com.acc.bookservice.exception.BookNotFoundException;
import com.acc.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service layer implementation for BookService
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    /**
     * This method returns list of all books
     *
     * @return
     */
    public List<Book> getBooks() {
        return bookDao.findAll();
    }

    /**
     * This method returns a book based on the isbnNumber
     *
     * @param isbnNumber
     * @return
     */
    public Optional<Book> getBookById(String isbnNumber) {
        return bookDao.findOne(isbnNumber);
    }

    /**
     * This method return books based on search criteria
     *
     * @param isbnNumber
     * @param title
     * @param author
     * @return
     */
    public List<Book> searchBooks(String isbnNumber, String title, String author) {
        List<Book> books = new ArrayList<>();
        if (Objects.nonNull(isbnNumber)) {
            Book book = getBookById(isbnNumber).orElseThrow(() -> new BookNotFoundException("Book details are not found. ISBN : " + isbnNumber));
            books.add(book);
        } else {
            books = bookDao.searchBooks(title, author);
        }
        return books;
    }
}
