package com.acc.bookservice;

import com.acc.bookservice.dao.BookDao;
import com.acc.bookservice.model.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookDaoTest {

    private BookDao bookDao = new BookDao();

    @Test
    public void findAllTest() {
        List<Book> books = bookDao.findAll();
        Assert.assertNotNull(books);
        Assert.assertEquals("Expected Size : ", 10, books.size());
    }

    @Test
    public void findOneTestWhenBookISBNExistsINTheListThenReturnBookDetails() {
        Book expectedBook = new Book("1000", "Book1", "Author1", "Summary1");
        Optional<Book> book = bookDao.findOne("1000");
        Assert.assertTrue("Book is not null : ", book.isPresent());
        Assert.assertEquals("Expected isbn : ", "1000", book.get().getIsbnNumber());
        Assert.assertEquals("Expected title : ", "Book1", book.get().getTitle());
        Assert.assertEquals("Expected Author : ", "Author1", book.get().getAuthor());
        Assert.assertEquals("Expected Summary : ", "Summary1", book.get().getSummary());
    }

    @Test
    public void findOneTestWhenBookISBNDoesExistsINTheListThenReturnBookDetails() {
        Optional<Book> book = bookDao.findOne("1010");
        Assert.assertFalse("Book is null : ", book.isPresent());
    }

    @Test
    public void searchBookTestWhenBookTitleIsNullAndAuthorNameIsPresentThenReturnBookDetailsForAuthor() {
        List<Book> books = bookDao.searchBooks(null, "Dan");
        Assert.assertNotNull("List of Books is not null : ", books);
        Assert.assertEquals("Expected Result Size : ", 3, books.size());
    }

    @Test
    public void searchBookTestWhenBookAuthorIsNullAndTitleIsPresentThenReturnBookDetailsForTitle() {
        List<Book> books = bookDao.searchBooks("of", null);
        Assert.assertNotNull("List of Books is not null : ", books);
        Assert.assertEquals("Expected Result Size : ", 5, books.size());
    }

    @Test
    public void searchBookTestWhenBookAuthorAndTitleIsPresentThenReturnBookDetailsForTitleAndAuthor() {
        List<Book> books = bookDao.searchBooks("Success", "Nap");
        Assert.assertNotNull("List of Books is not null : ", books);
        Assert.assertEquals("Expected Result Size : ", 3, books.size());
    }
}
