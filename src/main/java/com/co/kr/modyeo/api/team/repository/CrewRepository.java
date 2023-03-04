package com.co.kr.modyeo.api.team.repository;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.repository.custom.CrewCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew,Long>, CrewCustomRepository {
    CrewLevel findCrewLevelById(Long id);

    Crew findByTeamAndMember(Team team, Member member);
}
