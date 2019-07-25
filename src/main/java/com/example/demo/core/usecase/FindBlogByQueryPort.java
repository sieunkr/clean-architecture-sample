package com.example.demo.core.usecase;

import com.example.demo.core.domain.Blog;
import reactor.core.publisher.Mono;

public interface FindBlogByQueryPort {
    Mono<Blog> findBlogByQuery(String query);
}