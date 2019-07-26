package com.example.demo.core.usecase;

import com.example.demo.core.domain.Blog;
import reactor.core.publisher.Mono;

public interface BlogUseCase {
    Mono<Blog> findBlogByQuery(String query);
    Mono<Void> updateBlogByQuery(String query);
}