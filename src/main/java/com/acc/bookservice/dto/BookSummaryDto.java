package com.acc.bookservice.dto;

import java.math.BigDecimal;

/**
 * Another extension of BookBaseDto to have Book with summary
 */
public class BookSummaryDto extends BookBaseDto {
    private String summary;

    public BookSummaryDto() {
        super();
    }

    public BookSummaryDto(String isbnNumber, String title, String author, int quantity, BigDecimal price, String summary) {
        super(isbnNumber, title, author, quantity, price);
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "BookSummaryDto{" +
                "summary='" + summary + '\'' +
                '}';
    }
}
