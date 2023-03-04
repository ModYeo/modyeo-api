package com.co.kr.modyeo.api.team.repository;

import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.api.team.repository.custom.MemberTeamCustomRepository;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberTeamRepository extends JpaRepository<MemberTeam,Long> , MemberTeamCustomRepository {
    MemberTeam findByTeamAndMember(Team team, Member member);

    @Query("select mt from MemberTeam mt join fetch mt.member m join fetch mt.team t where mt.id = :id")
    Optional<MemberTeam> findMemberTeamById(@Param("id") Long id);

    List<MemberTeam> findByMember(Member member);
}
