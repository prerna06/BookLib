package com.acc.bookservice.controller;

import com.acc.bookservice.dto.BookBaseDto;
import com.acc.bookservice.dto.BookSummaryDto;
import com.acc.bookservice.exception.BookNotFoundException;
import com.acc.bookservice.exception.MissingSearchStringException;
import com.acc.bookservice.model.Book;
import com.acc.bookservice.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The class handles the requests for retrieving/Searching books
 */
@Api(value = "Book Retrieve/Search Api", description = "Apis pertaining to the operations on Book search")
@RestController
public class BookController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookService bookService;

    /**
     * This method returns all the books available in the inventory
     *
     * @return
     */
    @ApiOperation(value = "Get a list of available books", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "/books", produces = "application/json")
    public ResponseEntity<?> getBooks() {
        List<Book> books = bookService.getBooks();
        return new ResponseEntity(books.stream()
                .map(book -> modelMapper.map(book, BookBaseDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);

    }

    /**
     * This method returns a book for the given ISBN
     */
    @ApiOperation(value = "Get a book details based on isbn", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved book"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "/books/{isbnNumber}", produces = "application/json")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbnNumber) {
        Optional<Book> bookOptional = bookService.getBookById(isbnNumber);
        Book book = bookOptional.orElseThrow(() -> new BookNotFoundException("Book details are not found. ISBN : " + isbnNumber));
        return new ResponseEntity<BookSummaryDto>(modelMapper.map(book, BookSummaryDto.class), HttpStatus.OK);
    }

    /**
     * This method retun the list of books based on the criteria provided
     *
     * @param isbnNumber
     * @param title
     * @param author
     * @return
     */
    @ApiOperation(value = "View a list of available books based on filter criteria", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "/search", produces = "application/json")
    public ResponseEntity<?> searchBooks(@RequestParam(required = false, value = "isbnNumber") final String isbnNumber,
                                         @RequestParam(required = false, value = "title") final String title,
                                         @RequestParam(required = false, value = "author") final String author) {
        if (Objects.isNull(isbnNumber) && Objects.isNull(title) && Objects.isNull(title)) {
            throw new MissingSearchStringException("At least one search criteria is required.");
        }
        List<Book> books = bookService.searchBooks(isbnNumber, title, author);
        books.stream()
                .map(book -> modelMapper.map(book, BookBaseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity(books, HttpStatus.OK);
    }
}
