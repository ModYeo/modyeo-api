package com.co.kr.modyeo.api.notice.service.impl;

import com.co.kr.modyeo.api.notice.domain.dto.request.NoticeCreateRequest;
import com.co.kr.modyeo.api.notice.domain.dto.response.NoticeDetail;
import com.co.kr.modyeo.api.notice.domain.dto.response.NoticeResponse;
import com.co.kr.modyeo.api.notice.domain.entity.Notice;
import com.co.kr.modyeo.api.notice.repository.NoticeRepository;
import com.co.kr.modyeo.api.notice.service.NoticeService;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<NoticeResponse> getNotices() {
        return noticeRepository.findByUseYn(Yn.Y)
                .stream()
                .map(NoticeResponse::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NoticeDetail getNotice(Long id) {
        //TODO: 상세보기 만들어야함
        noticeRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .build());
        return null;
    }
}
