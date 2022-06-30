package com.co.kr.modyeo.member.repository;

import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.repository.custom.CrewCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrewRepository extends JpaRepository<Crew,Long>, CrewCustomRepository {
    Crew findByName(String name);
}
