package com.co.kr.modyeo.member.service;

import com.co.kr.modyeo.member.domain.entity.link.TeamCategory;

public interface TeamCategoryService {
    TeamCategory createTeamCategory(Long crewId, Long categoryId);

    void deleteTeamCategory(Long crewCategoryId);
}
