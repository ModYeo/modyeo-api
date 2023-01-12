package com.co.kr.modyeo.api.notice.service.impl;

import com.co.kr.modyeo.api.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeServiceImpl {

    private final NoticeRepository noticeRepository;
}
