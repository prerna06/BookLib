package com.acc.bookservice.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

/**
 * DTO to store list of book ordered with properties
 */
public class BookOrderedDto {

    private List<BookSummaryDto> bookDtos;

    public List<BookSummaryDto> getBookDtos() {
        return bookDtos;
    }

    public void setBookDtos(List<BookSummaryDto> bookDtos) {
        this.bookDtos = bookDtos;
    }

    @Override
    public String toString() {
        return "BookOrderedDto{" +
                "bookDtos=" + bookDtos +
                '}';
    }
}
