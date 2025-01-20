package hello.hello_spring.service;

import hello.hello_spring.domain.Member;

import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        // 외부에서 Repository나 class를 넣고 있다.
        // DI : Dependency injection
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void clean () {
        memberRepository.clearStore();
    }

    @Test
    void joinTest() {
        //given
        Member member = new Member();
        member.setName("same");

        //when
        Long saveId = memberService.join(member);


        //then
        Member result = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    public void validateDuplicateMemberTest() {
        //given
        Member member1 = new Member();
        member1.setName("same");

        Member member2 = new Member();
        member2.setName("same");

        //when

        memberService.join(member1);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}