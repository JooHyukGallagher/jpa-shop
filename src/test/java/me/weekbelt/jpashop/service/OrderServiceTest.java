package me.weekbelt.jpashop.service;

import me.weekbelt.jpashop.domain.Address;
import me.weekbelt.jpashop.domain.Item.Book;
import me.weekbelt.jpashop.domain.Item.Item;
import me.weekbelt.jpashop.domain.member.Member;
import me.weekbelt.jpashop.domain.order.Order;
import me.weekbelt.jpashop.domain.order.OrderRepository;
import me.weekbelt.jpashop.domain.order.OrderStatus;
import me.weekbelt.jpashop.exception.NotEnoughStockException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @DisplayName("상품주문")
    @Test
    public void createOrder() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(getOrder.getTotalPrice()).isEqualTo(10000 * 2);
        assertThat(item.getStockQuantity()).isEqualTo(8);
    }

    @DisplayName("상품주문시 재고수량 초과")
    @Test
    public void overLoadingOrderItem() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when
        //then
        assertThatThrownBy(() -> {
            orderService.order(member.getId(), item.getId(), orderCount);
        }).isInstanceOf(NotEnoughStockException.class).hasMessage("need more stock");
    }

    @DisplayName("주문 취소")
    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);
    }


    private Member createMember() {
        Member member = Member.builder()
                .name("회원1")
                .address(new Address("서울", "경기", "123-123"))
                .build();

        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = Book.builder()
                .name(name)
                .stockQuantity(stockQuantity)
                .price(price)
                .build();

        em.persist(book);
        return book;
    }
}
