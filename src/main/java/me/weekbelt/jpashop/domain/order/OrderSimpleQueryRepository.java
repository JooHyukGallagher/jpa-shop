package me.weekbelt.jpashop.domain.order;

import lombok.RequiredArgsConstructor;
import me.weekbelt.jpashop.web.dto.order.OrderSimpleQueryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                "SELECT new me.weekbelt.jpashop.web.dto.order.OrderSimpleQueryDto(" +
                        "o.id,  m.name, o.orderDate, o.status, d.address)" +
                        "FROM Order o" +
                        " JOIN o.member m" +
                        " JOIN o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
