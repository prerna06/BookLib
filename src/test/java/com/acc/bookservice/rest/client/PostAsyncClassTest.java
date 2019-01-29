package com.acc.bookservice.rest.client;

import com.acc.bookservice.rest.dto.PostsDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostAsyncClassTest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
    @InjectMocks
    private PostAsyncClient postAsyncClient;
    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getOrderDetailsTest() throws InterruptedException, ExecutionException {
        PostsDto postDto = new PostsDto(11, 1, "Title", "Summary");
        List<PostsDto> postsDtos = null;
        for (int i = 0; i < 3; i++) {
            postsDtos = restTemplate.postForObject(BASE_URL, postDto, List.class);
            Thread.sleep(1000L);
            Assert.assertNotNull(postDto);
        }
    }


}
