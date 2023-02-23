package com.co.kr.modyeo.api.geo.repository;

import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.geo.repository.custom.EmdAreaCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmdAreaRepository extends JpaRepository<EmdArea, Long>, EmdAreaCustomRepository {
}
