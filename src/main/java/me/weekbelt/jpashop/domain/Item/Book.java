package me.weekbelt.jpashop.domain.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter @NoArgsConstructor
@DiscriminatorValue("B")
@Entity
public class Book extends Item{

    private String author;
    private String isbn;

    @Builder
    public Book(String name, Integer price, Integer stockQuantity,
                 String author, String isbn){
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
