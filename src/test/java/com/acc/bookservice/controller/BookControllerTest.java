package com.acc.bookservice.controller;

import com.acc.bookservice.BookserviceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookserviceApplication.class)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getBooksTestWhenCalledReturnsListOfBooksWithOkStatus() throws Exception {
        mockMvc.perform(get("/books/1001"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getBookByIsbnTestWhenIsbnexistsThenReturnValidBookWithOkStatus() throws Exception {
        mockMvc.perform(get("/books/1001"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath(("$.isbnNumber"), is("1001")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getBookByIsbnTestWhenIsbnexistsThenReturnNotFoundStatus() throws Exception {
        mockMvc.perform(get("/books/1010"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath(("$.error"), containsString("not found")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void searchBooksWhenTitleAndAurthorDoNotMatcheThenReturnNotFoundStatus() throws Exception {
        mockMvc.perform(get("/search?\"author\"=\"test\"&\"title\"=\"test\""))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath(("$.error"), containsString("At least one")))
                .andDo(MockMvcResultHandlers.print());
    }

}
