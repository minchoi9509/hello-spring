package com.springboot.study.hellospring.service;

import com.springboot.study.hellospring.domain.Member;
import com.springboot.study.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    // 실무 할 때도 궁금했던 부분 -- 상위객체 = new 하위객체();
    // https://www.inflearn.com/questions/47449 관련 설명

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

//        // 중복 거르기
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // Optional을 통해서 여러 메서드 사용 가능
//        // ifNull을 통해 가지고 Null 체크 가능 * 찾아보기 필요
//        // orElse 통해서 값이 있을 때만 가져옴
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원");
//        });

        // 충격적으로 편리 ctrl+M 통해서 메서드 분리 가능
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원");
        });
    }

    /*
        전체 회원 조회
        서비스는 비즈니스 로직에 비슷하게 네이밍 / Repository의 경우는 데이터 친화적으로
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
