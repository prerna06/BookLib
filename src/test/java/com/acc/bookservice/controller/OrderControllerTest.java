package com.acc.bookservice.controller;

import com.acc.bookservice.BookserviceApplication;
import com.acc.bookservice.dto.BookOrderedDto;
import com.acc.bookservice.dto.BookSummaryDto;
import com.acc.bookservice.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookserviceApplication.class)
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void orderBooksTestWhenValidOrderThenReturnsListOfBooksWithOkStatus() throws Exception {
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(getOrderTestData()))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void orderBooksTestWhenBlankPayloadOrderThenReturnsInvalidDataException() throws Exception {
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(new OrderDto(null, null))))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void orderBooksTestWhenBlankRequestOrderThenReturnsInvalidDataException() throws Exception {
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(new OrderDto(null, null))))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void orderBooksTestWhenBlankUserIdOrderThenReturnsInvalidDataException() throws Exception {
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(getOrderWithoutUserIdTestData()))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    private String getOrderTestData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<BookSummaryDto> books = new ArrayList<>();
        books.add(new BookSummaryDto("1000", "Book1", "Author1", 1, new BigDecimal(100.0), "Summary1"));
        books.add(new BookSummaryDto("1001", "Book1", "Author1", 1, new BigDecimal(100.0), "Summary2"));

        BookOrderedDto bookOrderedDto = new BookOrderedDto();
        bookOrderedDto.setBookDtos(books);
        return objectMapper.writeValueAsString(new OrderDto("1234", bookOrderedDto));
    }

    private String getOrderWithoutUserIdTestData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<BookSummaryDto> books = new ArrayList<>();
        books.add(new BookSummaryDto("1000", "Book1", "Author1", 1, new BigDecimal(100.0), "Summary1"));
        books.add(new BookSummaryDto("1001", "Book1", "Author1", 1, new BigDecimal(100.0), "Summary2"));

        BookOrderedDto bookOrderedDto = new BookOrderedDto();
        bookOrderedDto.setBookDtos(books);
        return objectMapper.writeValueAsString(new OrderDto(null, bookOrderedDto));
    }

}