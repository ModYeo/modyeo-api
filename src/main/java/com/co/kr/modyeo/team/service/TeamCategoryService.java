package com.co.kr.modyeo.team.service;

import com.co.kr.modyeo.team.domain.entity.link.TeamCategory;

public interface TeamCategoryService {
    TeamCategory createTeamCategory(Long crewId, Long categoryId);

    void deleteTeamCategory(Long crewCategoryId);
}
