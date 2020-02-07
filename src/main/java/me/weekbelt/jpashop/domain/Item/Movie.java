package me.weekbelt.jpashop.domain.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@DiscriminatorValue("M")
@Entity
public class Movie extends Item {
    private String director;
    private String actor;

    @Builder
    public Movie(String name, Integer price, Integer stockQuantity,
                 String director, String actor) {
        super(name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }
}
