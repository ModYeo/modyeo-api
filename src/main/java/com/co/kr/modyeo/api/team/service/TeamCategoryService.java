package com.co.kr.modyeo.api.team.service;

public interface TeamCategoryService {
    Long createTeamCategory(Long crewId, Long categoryId);

    void deleteTeamCategory(Long crewCategoryId);
}
