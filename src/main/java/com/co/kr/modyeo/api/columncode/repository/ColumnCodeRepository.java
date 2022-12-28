package com.co.kr.modyeo.api.columncode.repository;

import com.co.kr.modyeo.api.columncode.domain.entity.ColumnCode;
import com.co.kr.modyeo.api.columncode.repository.custom.ColumnCodeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnCodeRepository extends JpaRepository<ColumnCode, Long>, ColumnCodeCustomRepository {
}
