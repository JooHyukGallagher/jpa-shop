package me.weekbelt.jpashop.domain.Delivery;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.weekbelt.jpashop.domain.Address;
import me.weekbelt.jpashop.domain.order.Order;

import javax.persistence.*;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Builder
    public Delivery(Address address, DeliveryStatus status) {
        this.address = address;
        this.status = status;
    }


    public void setOrder(Order order) {
        this.order = order;
    }
}
