package com.co.kr.modyeo.api.team.repository.custom;

import com.co.kr.modyeo.api.team.domain.entity.ApplicationForm;

public interface ApplicationFormCustomRepository {
    ApplicationForm findApplicationFormByTeamId(Long teamId);

    ApplicationForm findApplicationFormByMemberIdAndTeamId(String email, Long teamId);
}
