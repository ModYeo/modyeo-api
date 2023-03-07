package com.co.kr.modyeo.api.team.domain.spec;

import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.spec.AbstractSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CrewSpecification extends AbstractSpecification<CrewLevel> {
    @Override
    public boolean isSatisfiedBy(CrewLevel crewLevel) {
        return crewLevel != CrewLevel.NORMAL;
    }

    @Override
    public void check(CrewLevel crewLevel) throws ApiException {
        if (!isSatisfiedBy(crewLevel)){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode("NOT_AUTHORIZED")
                    .errorMessage("권한이 없습니다.")
                    .build();
        }

    }
}
