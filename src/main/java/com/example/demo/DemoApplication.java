package com.example.demo;

import com.example.demo.core.domain.Blog;
import com.example.demo.provider.SimpleRedisProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private final SimpleRedisProvider simpleRedisProvider;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Blog blog = new Blog();
        blog.setTotal(10);

        simpleRedisProvider.updateBlogByQuery("tset", blog);

    }
}
