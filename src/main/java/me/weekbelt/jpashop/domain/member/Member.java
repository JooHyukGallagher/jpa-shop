package me.weekbelt.jpashop.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.jpashop.domain.Address;
import me.weekbelt.jpashop.domain.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name= "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String name, Address address){
        this.name = name;
        this.address = address;
    }
}
