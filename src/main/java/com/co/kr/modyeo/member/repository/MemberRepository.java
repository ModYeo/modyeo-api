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

    @Query(value = "select m,mcl,cl from Member m left join m.memberCrewList mcl left join m.interestCategoryList cl")
    List<Member> findMembers();
}
