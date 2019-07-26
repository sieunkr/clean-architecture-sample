package com.example.demo.entry.rest;

import com.example.demo.core.domain.Blog;
import com.example.demo.core.usecase.BlogUseCase;
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

    private final BlogUseCase blogUseCase;

    @GetMapping("/articles")
    public Mono<Blog> findBlog(@RequestParam(name = "q") String query){
        return blogUseCase.findBlogByQuery(query);
    }
}