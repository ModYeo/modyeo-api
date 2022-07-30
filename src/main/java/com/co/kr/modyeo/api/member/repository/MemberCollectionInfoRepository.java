package com.co.kr.modyeo.api.member.repository;

import com.co.kr.modyeo.api.member.domain.entity.link.MemberCollectionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCollectionInfoRepository extends JpaRepository<MemberCollectionInfo,Long> {
}
