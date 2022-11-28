package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // 상속 테이블 전략 설정(부모 테이블에 해줌)
@DiscriminatorColumn(name = "dtype")    // 한 테이블에 다 들어가니까 구분을 할 때 어떻게 할 것인지 설정하는 것!
public abstract class Item {    // 구현체를 가지고 쓸 거라 추상 클래스로 만듬!

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
