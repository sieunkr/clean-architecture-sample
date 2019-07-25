package com.example.demo.core.service;

import com.example.demo.core.usecase.BlogUseCase;
import com.example.demo.core.usecase.FindBlogByQueryPort;
import com.example.demo.core.domain.Blog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class BlogService implements BlogUseCase {

    private final FindBlogByQueryPort findBlogByQueryPort;

    @Override
    public Mono<Blog> findBlogByQuery(String query) {

        return findBlogByQueryPort.findBlogByQuery(query);
    }
}