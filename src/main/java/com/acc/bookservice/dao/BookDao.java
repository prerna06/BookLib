package com.acc.bookservice.dao;

import com.acc.bookservice.model.Book;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * DAO class to have methods to retrieve Book details
 */
@Component
public class BookDao {

    private static List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book("1000", "Book1", "Author1", "Summary1"));
        bookList.add(new Book("1001", "Think And Grow Rich", "Napoleon Hill", "Napoleon Hill's classic Think and Grow Rich, has made more millionaires and inspired more successes than any other book in history. "));
        bookList.add(new Book("1002", "Wings of Fire: An Autobiography of Abdul Kalam", "Arun Tiwari", "he 'Wings of Fire' is one such autobiography by visionary scientist Dr. APJ Abdul Kalam, who from very humble beginnings rose to be the President of India."));
        bookList.add(new Book("1003", "Angels And Demons: (Robert Langdon Book 1)", "Dan Brown", "Summary4"));
        bookList.add(new Book("1004", "The Da Vinci Code", "Dan Brown", "Unless Langdon and Neveu can decipher the labyrinthine code and quickly assemble the pieces of the puzzle, a stunning historical truth will be lost forever."));
        bookList.add(new Book("1005", "Origin", "Dan Brown", "The spellbinding new Robert Langdon audiobook from the author of The Da Vinci Code"));
        bookList.add(new Book("1006", "365 Days Of Inspiration", "Napoleon Hill", "Principles for Personal and Professional Success"));
        bookList.add(new Book("1007", "Law Of Success", "Napoleon Hill", "A true masterpiece with the fundamentals of the Law of Success philosophy."));
        bookList.add(new Book("1008", "Law Of Success Edition2", "Napoleon Hill", "A true masterpiece with the fundamentals of the Law of Success philosophy."));
        bookList.add(new Book("1009", "Law Of Success Edition3", "Napoleon Hill", "A true masterpiece with the fundamentals of the Law of Success philosophy."));
    }

    public List<Book> findAll() {
        return bookList;
    }

    public Optional<Book> findOne(String isbnNumber) {
        Optional<Book> returnedBook = bookList.stream()
                .filter(book -> book.getIsbnNumber().equals(isbnNumber)).findFirst();
        return returnedBook;

    }

    /**
     * Return the list of books with partial title and partial author
     *
     * @param title
     * @param author
     * @return
     */
    public List<Book> searchBooks(String title, String author) {
        List<Book> books = null;
        if (Objects.isNull(author)) {
            books = bookList.stream().filter(book -> StringUtils.containsIgnoreCase(book.getTitle(), title)).collect(Collectors.toList());
        } else if (Objects.isNull(title)) {
            books = bookList.stream().filter(book -> StringUtils.containsIgnoreCase(book.getAuthor(), author)).collect(Collectors.toList());
        } else {
            books = bookList.stream().filter(book -> StringUtils.containsIgnoreCase(book.getTitle(), title) && StringUtils.containsIgnoreCase(book.getAuthor(), author))
                    .collect(Collectors.toList());
        }
        return books;
    }
}
