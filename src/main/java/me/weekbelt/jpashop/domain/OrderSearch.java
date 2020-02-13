package me.weekbelt.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.jpashop.domain.order.OrderStatus;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
