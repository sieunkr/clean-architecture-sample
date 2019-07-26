package com.example.demo.provider;

import com.example.demo.core.domain.Blog;
import com.example.demo.core.usecase.FindBlogByQueryPort;
import com.example.demo.core.usecase.UpdateBlogByQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SimpleRedisProvider implements FindBlogByQueryPort, UpdateBlogByQueryPort {

    private final ReactiveRedisOperations<String, Blog> blogReactiveRedisOperations;

    @Override
    public Mono<Blog> findBlogByQuery(String query) {
        return blogReactiveRedisOperations.opsForValue().get("test:" + query);
    }

    @Override
    public Mono<Void> updateBlogByQuery(String query, Blog blog) {

        blogReactiveRedisOperations.opsForValue().set("test:" + query, blog).subscribe();

        return Mono.empty();
    }
    
}
