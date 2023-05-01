package com.co.kr.modyeo.api.schedule.repository;

import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.repository.custom.SchedulerCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<Scheduler,Long>, SchedulerCustomRepository {
}
