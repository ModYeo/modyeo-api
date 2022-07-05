package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.entity.link.TeamCategory;

public interface TeamCategoryService {
    TeamCategory createTeamCategory(Long crewId, Long categoryId);

    void deleteTeamCategory(Long crewCategoryId);
}
