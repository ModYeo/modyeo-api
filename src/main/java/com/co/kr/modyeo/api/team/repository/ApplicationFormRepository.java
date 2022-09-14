package com.co.kr.modyeo.api.team.repository;

import com.co.kr.modyeo.api.team.domain.entity.ApplicationForm;
import com.co.kr.modyeo.api.team.repository.custom.ApplicationFormCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long>, ApplicationFormCustomRepository {
}
