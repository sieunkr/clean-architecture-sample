package com.example.demo.core.usecase;

import com.example.demo.core.domain.Blog;
import reactor.core.publisher.Mono;

public interface UpdateBlogByQueryPort {
    Mono<Void> updateBlogByQuery(String query, Blog blog);
}
