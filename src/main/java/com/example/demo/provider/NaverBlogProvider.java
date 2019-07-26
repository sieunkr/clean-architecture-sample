package com.example.demo.provider;

import com.example.demo.config.NaverBlogProperties;
import com.example.demo.core.domain.Blog;
import com.example.demo.core.usecase.FindBlogByQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@EnableConfigurationProperties({ NaverBlogProperties.class })
@RequiredArgsConstructor
public class NaverBlogProvider implements FindBlogByQueryPort {

    private final NaverBlogProperties naverBlogProperties;

    @Override
    public Mono<Blog> findBlogByQuery(String query) {

        Assert.notNull(query, "Query is not null");

        if(StringUtils.isEmpty(query)){
            return Mono.empty();
        }

        return WebClient.create(naverBlogProperties.getBlogUrl())
                .method(HttpMethod.GET)
                .uri("?query=" + query)
                .header("X-Naver-Client-Id", naverBlogProperties.getClientId())
                .header("X-Naver-Client-Secret", naverBlogProperties.getClientSecret())
                .retrieve()
                .bodyToMono(Blog.class);
    }
}
