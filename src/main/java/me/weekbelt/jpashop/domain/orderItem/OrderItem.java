package me.weekbelt.jpashop.domain.orderItem;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.jpashop.domain.Item.Item;
import me.weekbelt.jpashop.domain.order.Order;

import javax.persistence.*;

@Getter @NoArgsConstructor
@Entity @Table(name = "order_item")
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer orderPrice;
    private Integer count;

    @Builder
    public OrderItem(Integer orderPrice, Integer count){
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
