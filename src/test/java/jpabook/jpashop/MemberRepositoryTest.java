//package jpabook.jpashop;
//
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@RunWith(SpringRunner.class)    // JUnit에게 spring과 관련된 것으로 테스트를 할 것이라고 알려줌
//@SpringBootTest // spring-boot로 테스트 할 거니까 추가
//public class MemberRepositoryTest {
//
//    @Autowired MemberRepository memberRepository;
//
//    @Test
//    @Transactional  // EntityManager를 통한 모든 데이터 변경은 항상 Transaction 안에서 이루어져야 함!
//    // @Rollback(false)    // 기본적으로 test의 반복적 수행을 위해 한 번 수행 이후 roll back 되지만 눈으로 확인하고 싶다면 이 옵션을 사용하면 됨!
//    public void testMember() {
//        // given
//        Member member = new Member();   // member를 생성하고
//        member.setUsername("memberA");  // 이름 설정 후
//
//        // when
//        Long savedId = memberRepository.save(member);   // save한 뒤
//        Member findMember = memberRepository.find(savedId); // id를 기반으로 찾아봤을 때
//
//        // then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());    // member의 id 기반 확인
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());    // member의 username 기반 확인
//
//        // 같은 transaction 안에서 저장하고 조회하면 영속성 context가 똑같음. 같은 영속성 context 안에서는 id 값이 같으면 같은 엔티티로 인식
//        Assertions.assertThat(findMember).isEqualTo(member);
//        System.out.println("findMember == member: "+(findMember == member));
//    }
//
//}