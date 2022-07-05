package com.co.kr.modyeo.api.team.repository;

import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew,Long> {
}
