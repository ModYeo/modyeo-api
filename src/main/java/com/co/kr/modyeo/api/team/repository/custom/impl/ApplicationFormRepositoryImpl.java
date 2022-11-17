package com.co.kr.modyeo.api.team.repository.custom.impl;

import com.co.kr.modyeo.api.team.domain.entity.ApplicationForm;
import com.co.kr.modyeo.api.team.repository.custom.ApplicationFormCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;

import static com.co.kr.modyeo.api.team.domain.entity.QApplicationForm.applicationForm;
import static com.co.kr.modyeo.api.team.domain.entity.QTeam.team;

public class ApplicationFormRepositoryImpl extends Querydsl4RepositorySupport implements ApplicationFormCustomRepository {

    public ApplicationFormRepositoryImpl() {
        super(ApplicationForm.class);
    }

    @Override
    public ApplicationForm findApplicationFormByTeamId(Long teamId) {
        return select(applicationForm)
                .from(applicationForm)
                .innerJoin(applicationForm.team,team)
                .fetchJoin()
                .where(team.id.eq(teamId))
                .fetchOne();
    }
}
