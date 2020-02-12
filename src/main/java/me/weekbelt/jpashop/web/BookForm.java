package me.weekbelt.jpashop.web;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class BookForm {

    public Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
