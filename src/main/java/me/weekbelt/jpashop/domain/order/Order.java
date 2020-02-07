package me.weekbelt.jpashop.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.jpashop.domain.Delivery.Delivery;
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
    public void addDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
