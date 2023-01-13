package com.co.kr.modyeo.api.notice.service;

import com.co.kr.modyeo.api.notice.domain.dto.request.NoticeCreateRequest;

public interface NoticeService {
    Long createNotice(NoticeCreateRequest noticeCreateRequest);
}
