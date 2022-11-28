package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // Order와 Member는 다대일 관계
    @JoinColumn(name = "member_id") // member table의 primary key와 매핑
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)  // OrderItem 테이블에 있는 order 필드에 의해 mapping 되었음을 알려줌
    private List<OrderItem> orderItems = new ArrayList<>();

    /*
    cascade = CascadeType.All 옵션에 관하여 -> persist 전파!
    모든 엔티티는 기본적으로 persist를 각자 해주어야 함

    적용 전
    persist(orderItemA)
    persist(orderItemB)
    persist(orderItemC)
    persist(order)

    적용 후
    persist(order)
     */

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)    // order만 저장할 때 delivery도 같이 persist해줌
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;    // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    /*
    연관관계 메서드를 작성하지 않을 시
    Member member = new Member();
    Order order = new Order();

    member.getOrders().add(order);
    order.setMember(member);
     */
}
