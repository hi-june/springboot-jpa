package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // Column명 설정(안 해주면 변수 명인 id로 설정됩니다.
    private Long id;

    private String name;

    @Embedded   // 내장타입을 포함했다는 것을 annotation으로 확인해줌(Address 클래스에 있거나 여기 있거나 해도 되지만 코드상에서 확인을 위해 둘 다 써주기도 함)
    private Address address;

    // member와 order는 일대다 관계
    @OneToMany(mappedBy = "member") // order table에 있는 member필드에 의해서 mapping되었음을 표시!
    private List<Order> orders = new ArrayList<>();
}
