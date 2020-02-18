package me.weekbelt.jpashop.web.dto.order;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.jpashop.domain.Address;
import me.weekbelt.jpashop.domain.order.Order;
import me.weekbelt.jpashop.domain.order.OrderStatus;

import java.time.LocalDateTime;

@Getter @Setter
public class SimpleOrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
    }
}
