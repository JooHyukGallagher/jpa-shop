package me.weekbelt.jpashop.web.api;

import lombok.RequiredArgsConstructor;
import me.weekbelt.jpashop.domain.OrderSearch;
import me.weekbelt.jpashop.domain.order.Order;
import me.weekbelt.jpashop.domain.order.OrderRepository;
import me.weekbelt.jpashop.domain.order.OrderSimpleQueryRepository;
import me.weekbelt.jpashop.web.dto.order.OrderDto;
import me.weekbelt.jpashop.web.dto.order.OrderSimpleQueryDto;
import me.weekbelt.jpashop.web.dto.order.SimpleOrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> simpleOrdersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
        return result;
    }

    @GetMapping("/api/v4/simple-order")
    public List<OrderSimpleQueryDto> simpleOrdersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        List<OrderDto> result = orders.stream().map(OrderDto::new)
                .collect(toList());
        return result;
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> result = orders.stream().map(OrderDto::new)
                .collect(toList());
        return result;
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(@RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "100") int limit){
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
        List<OrderDto> result = orders.stream().map(OrderDto::new).collect(toList());
        return result;
    }
}
