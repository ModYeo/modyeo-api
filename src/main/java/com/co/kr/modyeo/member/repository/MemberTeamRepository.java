package com.co.kr.modyeo.member.repository;

import com.co.kr.modyeo.member.domain.entity.Team;
import com.co.kr.modyeo.member.domain.entity.Member;
import com.co.kr.modyeo.member.domain.entity.link.MemberTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTeamRepository extends JpaRepository<MemberTeam,Long> {
    MemberTeam findByTeamAndMember(Team team, Member member);
}
