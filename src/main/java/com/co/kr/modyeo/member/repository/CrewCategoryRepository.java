package com.co.kr.modyeo.member.repository;

import com.co.kr.modyeo.member.domain.entity.link.CrewCategory;
import com.co.kr.modyeo.member.repository.custom.CrewCategoryCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewCategoryRepository extends JpaRepository<CrewCategory,Long>, CrewCategoryCustomRepository {
}
