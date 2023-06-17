package com.co.kr.modyeo.api.schedule.repository;

import com.co.kr.modyeo.api.schedule.domain.entity.MemberScheduler;
import com.co.kr.modyeo.api.schedule.repository.custom.MemberSchedulerCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSchedulerRepository extends JpaRepository<MemberScheduler,Long>, MemberSchedulerCustomRepository {
}
