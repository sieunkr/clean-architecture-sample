package com.example.demo.entry.rest;

import com.example.demo.core.domain.Blog;
import com.example.demo.core.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public Mono<Blog> findBlog(@RequestParam(name = "q") String query){
        return blogService.findBlogByQuery(query);
    }

}
