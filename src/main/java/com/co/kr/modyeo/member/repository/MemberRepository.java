package com.co.kr.modyeo.member.repository;

import com.co.kr.modyeo.member.domain.entity.Member;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>,MemberCustomRepository {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query(value = "select m from Member m join fetch m.memberCrewList mcl join fetch m.interestCategoryList icl")
    List<Member> findMembers();
}
