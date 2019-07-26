package com.example.demo.core.service;

import com.example.demo.core.usecase.BlogUseCase;
import com.example.demo.core.usecase.FindBlogByQueryPort;
import com.example.demo.core.domain.Blog;
import com.example.demo.core.usecase.UpdateBlogByQueryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class BlogService implements BlogUseCase {

    private final FindBlogByQueryPort findBlogByQueryPort;
    private final UpdateBlogByQueryPort updateBlogByQueryPort;

    public BlogService(FindBlogByQueryPort simpleRedisProvider, UpdateBlogByQueryPort updateBlogByQueryPort) {
        this.findBlogByQueryPort = simpleRedisProvider;
        this.updateBlogByQueryPort = updateBlogByQueryPort;
    }

    @Override
    public Mono<Blog> findBlogByQuery(String query) {

        return findBlogByQueryPort.findBlogByQuery(query);
    }

    @Override
    public Mono<Void> updateBlogByQuery(String query) {

        updateBlogByQueryPort.updateBlogByQuery(query, findBlogByQueryPort.findBlogByQuery(query).block());

        return Mono.empty();
    }

}