package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    // 객체는 다대다 관계가 가능하지만, 관계형 db는 컬렉션 관계를 양쪽 다 가질 수 있는 것이 아니기 때문에 일대다 다대일로 풀어내는 중간 테이블이 있어야 함
    // 실무에서는 안 쓰임 -> 더 필드를 추가하는 것이 불가능하기 때문
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();


    // 카테고리의 계층 구조의 표현 -> 일종의 셀프로 양방향 연관관계 만들기
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
