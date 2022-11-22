package com.co.kr.modyeo.api.member.block.repository;

import com.co.kr.modyeo.api.member.block.domain.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block,Long> {
}
