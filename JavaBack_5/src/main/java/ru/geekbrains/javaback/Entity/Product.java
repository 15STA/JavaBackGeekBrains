package ru.geekbrains.javaback.Entity;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@ToString
public class Product {
   Integer Id;
   String title;
   Integer price;
   String categoryTitle;
}
