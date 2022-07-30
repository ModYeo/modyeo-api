package com.co.kr.modyeo.api.member.collection.repository;

import com.co.kr.modyeo.api.member.collection.domain.entity.CollectionInfo;
import com.co.kr.modyeo.api.member.collection.repository.custom.CollectionInfoCustomRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionInfoRepository extends JpaRepository<CollectionInfo, Long>, CollectionInfoCustomRepository {
    List<CollectionInfo> findByUseYn(Yn useYn);
}
