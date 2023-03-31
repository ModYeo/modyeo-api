package com.co.kr.modyeo.api.member.repository;

import com.co.kr.modyeo.api.member.collection.domain.entity.CollectionInfo;
import com.co.kr.modyeo.api.member.domain.entity.link.MemberCollectionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberCollectionInfoRepository extends JpaRepository<MemberCollectionInfo,Long> {
    List<MemberCollectionInfo> findByCollectionInfo(CollectionInfo collectionInfo);

    void deleteByCollectionInfo(CollectionInfo collectionInfo);
}
