package com.example.demo.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class Blog {

    private Integer total;
    private List<Item> items;

    @Data
    public static class Item{
        String title;
        String link;
        String bloggername;
        String description;
        String postdate;
    }
}
