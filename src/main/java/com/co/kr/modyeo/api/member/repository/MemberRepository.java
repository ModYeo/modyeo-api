package com.co.kr.modyeo.api.member.repository;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.custom.MemberCustomRepository;
import com.querydsl.core.group.GroupBy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    boolean existsByNickname(String nickname);
}
