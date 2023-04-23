package com.co.kr.modyeo.api.schedule.repository;

import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<Scheduler,Long> {
}
