package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    private Address address;

    @Enumerated(EnumType.STRING)    // EnumType의 디폴트가 Ordinal임 --> column이 숫자로 들어감 --> 중간에 다른 상태가 생기면 망함(순서가 밀리게 됨) --> 따라서 꼭 string으로 쓸 것!
    private DeliveryStatus status;  // READY(준비), COMP(배송)
}
