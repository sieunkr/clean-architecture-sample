package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "naver.openapi")
public class NaverBlogProperties {

    private String blogUrl;
    private String clientId;
    private String clientSecret;
}