package com.example.demo.core.service;

import com.example.demo.core.usecase.BlogUseCase;
import com.example.demo.core.usecase.FindBlogByQueryPort;
import com.example.demo.core.domain.Blog;
import com.example.demo.core.usecase.UpdateBlogByQueryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class BlogService implements BlogUseCase {

    private final FindBlogByQueryPort simpleRedisProvider;
    private final FindBlogByQueryPort naverBlogProvider;
    private final UpdateBlogByQueryPort updateBlogByQueryPort;

    public BlogService(FindBlogByQueryPort simpleRedisProvider, FindBlogByQueryPort naverBlogProvider, UpdateBlogByQueryPort updateBlogByQueryPort) {
        this.simpleRedisProvider = simpleRedisProvider;
        this.naverBlogProvider = naverBlogProvider;
        this.updateBlogByQueryPort = updateBlogByQueryPort;
    }

    @Override
    public Mono<Blog> findBlogByQuery(String query) {

        return simpleRedisProvider.findBlogByQuery(query);
    }

    @Override
    public Mono<Void> updateBlogByQuery(String query) {

        naverBlogProvider.findBlogByQuery(query).subscribe(blog -> {
            updateBlogByQueryPort.updateBlogByQuery(query, blog);
        });

        return Mono.empty();
    }

}