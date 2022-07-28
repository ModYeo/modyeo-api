package com.co.kr.modyeo.api.member.repository;

import com.co.kr.modyeo.api.member.domain.entity.link.MemberCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory, Long> {
}
