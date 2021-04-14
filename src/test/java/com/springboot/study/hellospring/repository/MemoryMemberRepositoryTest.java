package com.springboot.study.hellospring.repository;

import com.springboot.study.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 전체 실행 시 진행 순서가 없어서 의존적이지 않게 개발 해야 함.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("mingu");

        repository.save(member);

        // Optional 클래스 -> get() 으로 return
        Member result = repository.findById(member.getId()).get();
        // Junit 검증 메서드
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
    }


}
