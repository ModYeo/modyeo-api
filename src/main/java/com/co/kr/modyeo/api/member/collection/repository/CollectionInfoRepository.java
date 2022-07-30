package com.co.kr.modyeo.api.member.collection.repository;

import com.co.kr.modyeo.api.member.collection.domain.entity.CollectionInfo;
import com.co.kr.modyeo.api.member.collection.repository.custom.CollectionInfoCustomRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionInfoRepository extends JpaRepository<CollectionInfo, Long>, CollectionInfoCustomRepository {

    List<CollectionInfo> findByUseYn(Yn useYn);

    @Query(value = "select ci from CollectionInfo ci where ci.id in :idList")
    List<CollectionInfo> findByIdList(@Param(value = "idList") List<Long> idList);
}
