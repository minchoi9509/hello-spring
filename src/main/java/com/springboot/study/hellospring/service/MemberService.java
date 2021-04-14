package com.springboot.study.hellospring.service;

import com.springboot.study.hellospring.repository.MemberRepository;
import com.springboot.study.hellospring.repository.MemoryMemberRepository;

public class MemberService {

    // 실무 할 때도 궁금했던 부분 -- 상위객체 = new 하위객체();
    // https://www.inflearn.com/questions/47449 관련 설명

    private final MemberRepository memberRepository = new MemoryMemberRepository();

}
