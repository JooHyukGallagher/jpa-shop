package me.weekbelt.jpashop.domain.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter @NoArgsConstructor
@DiscriminatorValue("A")
@Entity
public class Album extends Item{

    private String artist;
    private String etc;

    @Builder
    public Album(String name, Integer price, Integer stockQuantity,
                 String artist, String etc){
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }

}
