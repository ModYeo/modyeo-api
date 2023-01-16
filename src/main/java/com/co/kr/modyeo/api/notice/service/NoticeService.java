package com.co.kr.modyeo.api.notice.service;

import com.co.kr.modyeo.api.notice.domain.dto.request.NoticeCreateRequest;
import com.co.kr.modyeo.api.notice.domain.dto.request.NoticeUpdateRequest;
import com.co.kr.modyeo.api.notice.domain.dto.response.NoticeDetail;
import com.co.kr.modyeo.api.notice.domain.dto.response.NoticeResponse;

import java.util.List;

public interface NoticeService {
    Long createNotice(NoticeCreateRequest noticeCreateRequest);

    List<NoticeResponse> getNotices();

    NoticeDetail getNotice(Long id);

    Long updateNotice(NoticeUpdateRequest noticeUpdateRequest);
}
