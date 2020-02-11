package me.weekbelt.jpashop.domain;

import lombok.Getter;
import me.weekbelt.jpashop.domain.order.OrderStatus;

@Getter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
