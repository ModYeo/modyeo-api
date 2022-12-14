package com.co.kr.modyeo.api.block.repository;

import com.co.kr.modyeo.api.block.domain.entity.Block;
import com.co.kr.modyeo.api.block.repository.custom.BlockCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block,Long>, BlockCustomRepository {
}
