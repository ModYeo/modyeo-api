package com.co.kr.modyeo.api.member.collection.repository;

import com.co.kr.modyeo.api.member.collection.domain.entity.CollectionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionInfoRepository extends JpaRepository<CollectionInfo, Long> {
}
