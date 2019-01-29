package com.acc.bookservice.rest.client;

import com.acc.bookservice.dto.BookSummaryDto;
import com.acc.bookservice.dto.OrderDto;
import com.acc.bookservice.rest.dto.PostsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * An async client for the Posts service
 */
@Service
public class PostAsyncClient {

    private static Logger log = LoggerFactory.getLogger(PostAsyncClient.class);

    @Autowired
    private RestTemplate restTemplate;

    private static Map<String, String> prepareRequestData(OrderDto orderDto, BookSummaryDto bookSummaryDTO) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userId", orderDto.getUserId());
        paramMap.put("id", bookSummaryDTO.getIsbnNumber());
        paramMap.put("title", bookSummaryDTO.getTitle());
        paramMap.put("body", bookSummaryDTO.getSummary());
        return paramMap;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Method that Gets the posts details
     *
     * @param orderDto
     * @return
     * @throws InterruptedException
     */
    @Async("asyncExecutor")
    public CompletableFuture<List<PostsDto>> getOrderDetails(OrderDto orderDto) throws InterruptedException {
        log.info("getOrderDetails Starts");
        List<BookSummaryDto> books = orderDto.getBookOrderedDto().getBookDtos();
        List<PostsDto> postsDtos = null;
        Map<String, String> stringStringMap = null;

        for (BookSummaryDto bookSummaryDTO : books) {
            stringStringMap = prepareRequestData(orderDto, bookSummaryDTO);
            postsDtos = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class, stringStringMap);
            Thread.sleep(1000L);    //Intentional delay
            log.info("getOrderDetails completed");

        }
        return CompletableFuture.completedFuture(postsDtos);
    }


}
