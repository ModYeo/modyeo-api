package com.co.kr.modyeo.api.team.repository;

import com.co.kr.modyeo.api.team.domain.entity.link.TeamCategory;
import com.co.kr.modyeo.api.team.repository.custom.TeamCategoryCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamCategoryRepository extends JpaRepository<TeamCategory,Long>, TeamCategoryCustomRepository {
}
