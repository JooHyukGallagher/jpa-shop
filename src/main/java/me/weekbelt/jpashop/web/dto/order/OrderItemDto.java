package me.weekbelt.jpashop.web.dto.order;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.jpashop.domain.orderItem.OrderItem;

@Getter @Setter
public class OrderItemDto {

    private String itemName;//상품 명
    private int orderPrice; //주문 가격
    private int count; //주문 수량

    public OrderItemDto(OrderItem orderItem) {
        itemName = orderItem.getItem().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}
