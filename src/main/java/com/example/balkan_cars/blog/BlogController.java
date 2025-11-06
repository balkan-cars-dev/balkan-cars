package com.example.balkan_cars.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    
    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<BlogDto> getBlogContent() {
        try {
            return blogService.fetchCarFeed();
        } catch (Exception e) {
            throw new RuntimeException("Cannot fetch blog content", e);
        }
    }
}
