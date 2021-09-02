package ru.geekbrains.javaback.Entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Category {
    Integer id;
    String title;
    ArrayList<Product> products;
}
