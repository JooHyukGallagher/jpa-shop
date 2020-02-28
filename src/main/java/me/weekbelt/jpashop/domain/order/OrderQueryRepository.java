package me.weekbelt.jpashop.domain.order;

import lombok.RequiredArgsConstructor;
import me.weekbelt.jpashop.web.dto.order.OrderItemQueryDto;
import me.weekbelt.jpashop.web.dto.order.OrderQueryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos(){
        List<OrderQueryDto> result = findOrders();
        result.forEach(o -> {
            List<OrderItemQueryDto> orderItems = findOrderItem(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return result;
    }

    private List<OrderQueryDto> findOrders() {
        return em.createQuery(
                "SELECT new me.weekbelt.jpashop.web.dto.order.OrderQueryDto(" +
                        "o.id, m.name, o.orderDate, o.status, d.address)" +
                        " FROM Order o" +
                        " JOIN o.member m" +
                        " JOIN o.delivery d", OrderQueryDto.class)
                .getResultList();
    }

    private List<OrderItemQueryDto> findOrderItem(Long orderId) {
        return em.createQuery(
                "SELECT new me.weekbelt.jpashop.web.dto.order.OrderItemQueryDto(" +
                        "oi.order.id, i.name, oi.orderPrice, oi.count)" +
                        "FROM OrderItem oi" +
                        " JOIN oi.item i" +
                        " WHERE oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(toOrderIds(result));

        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return result;
    }

    private List<Long> toOrderIds(List<OrderQueryDto> result) {
        return result.stream()
                .map(OrderQueryDto::getOrderId)
                .collect(Collectors.toList());
    }

    private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds){
        List<OrderItemQueryDto> orderItems = em.createQuery(
                "SELECT new me.weekbelt.jpashop.web.dto.order.OrderItemQueryDto(" +
                        "oi.order.id, i.name, oi.orderPrice, oi.count)" +
                        " FROM OrderItem oi" +
                        " JOIN oi.item i" +
                        " WHERE oi.order.id in :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        return orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
    }
}
