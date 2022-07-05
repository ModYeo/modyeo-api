package com.co.kr.modyeo.team.repository;

import com.co.kr.modyeo.team.domain.entity.link.TeamCategory;
import com.co.kr.modyeo.team.repository.custom.TeamCategoryCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamCategoryRepository extends JpaRepository<TeamCategory,Long>, TeamCategoryCustomRepository {
}
