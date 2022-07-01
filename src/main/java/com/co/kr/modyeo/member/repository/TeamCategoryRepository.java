package com.co.kr.modyeo.member.repository;

import com.co.kr.modyeo.member.domain.entity.link.TeamCategory;
import com.co.kr.modyeo.member.repository.custom.TeamCategoryCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamCategoryRepository extends JpaRepository<TeamCategory,Long>, TeamCategoryCustomRepository {
}
