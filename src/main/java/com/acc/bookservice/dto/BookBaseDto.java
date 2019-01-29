package com.acc.bookservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

/**
 * Base DTO having basic properties of a Book
 */
public class BookBaseDto {

    private String isbnNumber;

    private String title;

    private String author;
    @JsonIgnore
    private int quantity;
    @JsonIgnore
    private BigDecimal price;

    public BookBaseDto() {
        super();
    }

    public BookBaseDto(String isbnNumber, String title, String author, int quantity, BigDecimal price) {
        super();
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookBaseDto{" +
                "isbnNumber='" + isbnNumber + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
