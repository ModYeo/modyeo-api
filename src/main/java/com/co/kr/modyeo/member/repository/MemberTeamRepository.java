package com.co.kr.modyeo.member.repository;

import com.co.kr.modyeo.member.domain.entity.Team;
import com.co.kr.modyeo.member.domain.entity.Member;
import com.co.kr.modyeo.member.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.member.repository.custom.MemberTeamCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberTeamRepository extends JpaRepository<MemberTeam,Long> , MemberTeamCustomRepository {
    MemberTeam findByTeamAndMember(Team team, Member member);

    @Query("select mt from MemberTeam mt join fetch mt.member m join fetch mt.team t where mt.id = :id")
    Optional<MemberTeam> findMemberTeamById(@Param("id") Long id);
}
