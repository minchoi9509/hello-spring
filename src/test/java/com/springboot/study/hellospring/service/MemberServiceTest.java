package com.springboot.study.hellospring.service;

import com.springboot.study.hellospring.domain.Member;
import com.springboot.study.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// ctrl+shift+T 를 통해서 Test 파일 생성 가능
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // 외부에서 memberRepository를 주입하기 때문에 DI 라고 함.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // 테스트 문법

        // given (주어진 상황)
        Member member = new Member();
        member.setName("ming");

        // when
        Long saveId = memberService.join(member);

        // then (결과)
        // ctrl + alt V로 Return 형 자동으로 받아 올 수 있음
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원가입_예외() {
        // 테스트는 정상 작동도 중요하지만 예외 상황일 때 로직이 잘 들어가는지가 더 중요

        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // 1. 예외처리를 잡으려면 try-cath문 사용 할 수 있음.

//        try {
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
//        }

        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}