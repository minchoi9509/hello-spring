package com.springboot.study.hellospring.repository;

import com.springboot.study.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    // Optional: null 처리를 Optional 객체를 통해서 반환 (Java 8)
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
    void clearStore();

}
