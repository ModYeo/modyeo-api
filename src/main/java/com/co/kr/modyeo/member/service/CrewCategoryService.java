package com.co.kr.modyeo.member.service;

import com.co.kr.modyeo.member.domain.entity.link.CrewCategory;

public interface CrewCategoryService {
    CrewCategory createCrewCategory(Long crewId, Long categoryId);

    void deleteCrewCategory(Long crewCategoryId);
}
