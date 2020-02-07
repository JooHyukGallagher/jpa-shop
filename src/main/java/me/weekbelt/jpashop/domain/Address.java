package me.weekbelt.jpashop.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
