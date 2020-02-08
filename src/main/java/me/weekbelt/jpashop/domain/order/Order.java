package me.weekbelt.jpashop.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.jpashop.domain.Delivery.Delivery;
import me.weekbelt.jpashop.domain.Delivery.DeliveryStatus;
import me.weekbelt.jpashop.domain.member.Member;
import me.weekbelt.jpashop.domain.orderItem.OrderItem;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity @Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    public Order(LocalDateTime orderDate, OrderStatus status){
        this.orderDate = orderDate;
        this.status = status;
    }

    // Member 엔티티와 양방향 연관관계
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    // OrderItem 엔티티와 양방향 연관관계
    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // Delivery 엔티티와 양방향 연관관계
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // == 생성 메서드 == //
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = Order.builder()
                .status(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .build();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }

        return order;
    }

    // == 비즈니스 로직 == //
    /** 주문 취소 */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능 합니다.");
        }
        changeOrderStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    private void changeOrderStatus(OrderStatus status){
        this.status = status;
    }

    // == 조회 로직 == //
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
