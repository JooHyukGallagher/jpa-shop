package me.weekbelt.jpashop.web.dto.order;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.jpashop.domain.Address;
import me.weekbelt.jpashop.domain.order.Order;
import me.weekbelt.jpashop.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter @Setter
public class OrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
        orderItems = order.getOrderItems().stream()
                .map(OrderItemDto::new)
                .collect(toList());
    }
}
