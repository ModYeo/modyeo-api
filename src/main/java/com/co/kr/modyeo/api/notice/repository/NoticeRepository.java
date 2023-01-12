package com.co.kr.modyeo.api.notice.repository;

import com.co.kr.modyeo.api.notice.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
