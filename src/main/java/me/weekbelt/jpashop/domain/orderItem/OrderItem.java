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

    public void setItem(Item item) {
        this.item = item;
    }

    // 생성 메서드 //
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem= OrderItem.builder()
                .orderPrice(orderPrice)
                .count(count)
                .build();
        orderItem.setItem(item);

        item.removeStock(count);
        return orderItem;
    }

    // == 비즈니스 로직 == //
    /** 주문 취소 */
    public void cancel(){
        getItem().addStock(count);
    }

    // == 조회 로직 == //
    /** 주문상품 전체 가격 조회 */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
