package me.weekbelt.jpashop.web.dto.order;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.jpashop.domain.Address;
import me.weekbelt.jpashop.domain.order.OrderStatus;

import java.time.LocalDateTime;

@Getter @Setter
public class OrderSimpleQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
