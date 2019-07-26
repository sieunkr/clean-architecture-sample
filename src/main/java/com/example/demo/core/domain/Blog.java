package com.example.demo.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {

    private Integer total;
    private List<Item> items;

    @Data
    public static class Item implements Serializable{
        String title;
        String link;
        String bloggername;
        String description;
        String postdate;
    }
}
