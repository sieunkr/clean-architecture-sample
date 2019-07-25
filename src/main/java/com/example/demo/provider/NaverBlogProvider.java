package com.example.demo.provider;

import com.example.demo.core.domain.Blog;
import com.example.demo.core.usecase.FindBlogByQueryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NaverBlogProvider implements FindBlogByQueryPort {

    @Value("${naver.openapi.blog.url}")
    private String naverOpenApiUrl;

    @Value("${naver.openapi.client.id}")
    private String naverOpenApiClientId;

    @Value("${naver.openapi.client.secret}")
    private String naverOpenApiClientSecret;

    @Override
    public Mono<Blog> findBlogByQuery(String query) {

        return WebClient.create(naverOpenApiUrl)
                .method(HttpMethod.GET)
                .uri("?query=" + query)
                .header("X-Naver-Client-Id", naverOpenApiClientId)
                .header("X-Naver-Client-Secret", naverOpenApiClientSecret)
                .retrieve()
                .bodyToMono(Blog.class);

    }
}
