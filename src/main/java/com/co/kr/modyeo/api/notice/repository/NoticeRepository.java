package com.co.kr.modyeo.api.notice.repository;

import com.co.kr.modyeo.api.notice.domain.entity.Notice;
import com.co.kr.modyeo.common.enumerate.Yn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByUseYn(Yn useYn);
}
