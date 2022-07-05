package com.co.kr.modyeo.api.member.repository;

import com.co.kr.modyeo.api.member.repository.custom.MemberCustomRepository;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query(value = "select m,mcl,cl from Member m left join m.memberTeamList mcl left join m.interestCategoryList cl")
    List<Member> findMembers();
}
