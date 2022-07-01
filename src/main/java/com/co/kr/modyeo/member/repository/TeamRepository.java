package com.co.kr.modyeo.member.repository;

import com.co.kr.modyeo.member.domain.entity.Team;
import com.co.kr.modyeo.member.repository.custom.TeamCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team,Long>, TeamCustomRepository {
    Team findByName(String name);

    @Query(value = "select t from Team t join fetch t.categoryList")
    Optional<Team> findTeamById(Long id);
}
