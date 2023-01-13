package com.co.kr.modyeo.api.notice.service.impl;

import com.co.kr.modyeo.api.notice.domain.dto.request.NoticeCreateRequest;
import com.co.kr.modyeo.api.notice.domain.entity.Notice;
import com.co.kr.modyeo.api.notice.repository.NoticeRepository;
import com.co.kr.modyeo.api.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public Long createNotice(NoticeCreateRequest noticeCreateRequest) {
        Notice notice = NoticeCreateRequest.toEntity(noticeCreateRequest);
        return noticeRepository.save(notice).getId();
    }
}
