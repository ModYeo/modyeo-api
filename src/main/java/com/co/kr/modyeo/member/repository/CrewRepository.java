package com.co.kr.modyeo.member.repository;

import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.repository.custom.CrewCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew,Long>, CrewCustomRepository {
}
