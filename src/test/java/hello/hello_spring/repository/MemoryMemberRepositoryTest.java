package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("hi");

        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findById(member.getId()).get();
        //    System.out.println("result = " + (result == member));
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("jeric");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("jeric2");
        memoryMemberRepository.save(member2);

        Member result1 = memoryMemberRepository.findByName("jeric").get();
        assertThat(result1).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("jericKim");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("jericKim");
        memoryMemberRepository.save(member2);

        List<Member> result = memoryMemberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
