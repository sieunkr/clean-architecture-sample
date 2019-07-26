package com.example.demo.provider;

import com.example.demo.core.domain.Blog;
import com.example.demo.core.usecase.FindBlogByQueryPort;
import com.example.demo.core.usecase.UpdateBlogByQueryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class SimpleRedisProvider implements FindBlogByQueryPort, UpdateBlogByQueryPort {

    private final ReactiveRedisOperations<String, Blog> blogReactiveRedisOperations;
    private final String BLOG_ARTICLES_REDIS_KEY = "blog:articles:";

    @Override
    public Mono<Blog> findBlogByQuery(String query) {
        return blogReactiveRedisOperations.opsForValue().get(BLOG_ARTICLES_REDIS_KEY + query);
    }

    @Override
    public Mono<Void> updateBlogByQuery(String query, Blog blog) {

        blogReactiveRedisOperations.opsForValue().set(BLOG_ARTICLES_REDIS_KEY + query, blog)
                .subscribe(null,
                        e -> log.info("update error"),
                        () -> log.info("update success"));


        return Mono.empty();
    }
}
