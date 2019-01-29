package com.acc.bookservice.dto;

import javax.validation.constraints.NotNull;

/**
 * DTO to have Order details
 */
public class OrderDto {

    @NotNull
    private String userId;

    @NotNull
    private BookOrderedDto bookOrderedDto;

    public OrderDto(String userId, BookOrderedDto bookOrderedDto) {
        super();
        this.userId = userId;
        this.bookOrderedDto = bookOrderedDto;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BookOrderedDto getBookOrderedDto() {
        return bookOrderedDto;
    }

    public void setBookOrderedDto(BookOrderedDto bookOrderedDto) {
        this.bookOrderedDto = bookOrderedDto;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "userId='" + userId + '\'' +
                ", bookOrderedDto=" + bookOrderedDto +
                '}';
    }

}
