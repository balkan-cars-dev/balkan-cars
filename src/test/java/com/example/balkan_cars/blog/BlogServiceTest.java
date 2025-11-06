package com.example.balkan_cars.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceTest {
    
    @Autowired
    private BlogService blogService;
    
    @Test
    void fetchCarFeed() throws Exception {
        List<BlogDto> blogs = blogService.fetchCarFeed();
        assertNotNull(blogs);
    }
    
    @Test
    void fetchBookFeed_shouldSetRandomId() throws Exception {
        List<BlogDto> blogs = blogService.fetchCarFeed();
        assertNotNull(blogs);
        assertNotNull(blogs.get(0).id());
    }

}