package com.acc.bookservice.service;

import com.acc.bookservice.dao.BookDao;
import com.acc.bookservice.exception.BookNotFoundException;
import com.acc.bookservice.model.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    List<Book> bookList = new ArrayList<>();
    @Mock
    private BookDao bookDao;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void getBooksTestReturnAllBooks() {
        when(bookDao.findAll()).thenReturn(bookList);
        List<Book> actualBookList = bookService.getBooks();
        Assert.assertNotNull("Retuned list is not null : ", actualBookList);
        Assert.assertEquals("Returned list Size : ", bookList.size(), actualBookList.size());
    }

    @Test
    public void getBookByIdTest() {
        Book expectedBook = new Book("1000", "Book1", "Author1", "Summary1");
        when(bookDao.findOne(any())).thenReturn(Optional.of(expectedBook));
        Optional<Book> actualBook = bookService.getBookById("1000");
        Assert.assertTrue("Retuned Book is present : ", actualBook.isPresent());
        Assert.assertEquals("Returned Book ISBN : ", expectedBook.getIsbnNumber(), actualBook.get().getIsbnNumber());
    }

    @Test(expected = BookNotFoundException.class)
    public void getBookByIdTestWhenWrong() {
        when(bookDao.findOne("1010")).thenThrow(new BookNotFoundException("Book not found"));
        Optional<Book> actualBook = bookService.getBookById("1010");
    }

    @Test
    public void searchBookTestWhenIsbnIsMentionedReturnOneBook() {
        Book expectedBook = new Book("1000", "Book1", "Author1", "Summary1");
        when(bookDao.findOne(any())).thenReturn(Optional.of(expectedBook));
        List<Book> actualBookList = bookService.searchBooks("1000", null, null);
        Assert.assertFalse("Returned Book is present : ", actualBookList.isEmpty());
        Assert.assertEquals("Returned Book ISBN : ", expectedBook.getIsbnNumber(), actualBookList.get(0).getIsbnNumber());
    }

    @Test
    public void searchBookTestWhenIsbnIsMissingAndTitleisMentionedReturnOneBookBookList() {
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(new Book("1010", "Book1", "Author1", "Summary1"));
        when(bookDao.searchBooks(any(), any())).thenReturn(expectedList);
        List<Book> actualBookList = bookService.searchBooks(null, "Book", null);
        Assert.assertFalse("Retuned Book List : ", actualBookList.isEmpty());
        Assert.assertEquals("Returned Book ISBN : ", expectedList.get(0).getIsbnNumber(), actualBookList.get(0).getIsbnNumber());
    }

    private List<Book> getTestDataListOfBooks() {
        bookList.add(new Book("1000", "Book1", "Author1", "Summary1"));
        bookList.add(new Book("1001", "Think And Grow Rich", "Napoleon Hill", "Napoleon Hill's classic Think and Grow Rich, has made more millionaires and inspired more successes than any other book in history. "));
        return bookList;
    }
}
