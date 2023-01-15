package com.co.kr.modyeo.api.notice.service.impl;

import com.co.kr.modyeo.api.notice.domain.dto.request.NoticeCreateRequest;
import com.co.kr.modyeo.api.notice.domain.dto.request.NoticeUpdateRequest;
import com.co.kr.modyeo.api.notice.domain.dto.response.NoticeDetail;
import com.co.kr.modyeo.api.notice.domain.dto.response.NoticeResponse;
import com.co.kr.modyeo.api.notice.domain.entity.Notice;
import com.co.kr.modyeo.api.notice.repository.NoticeRepository;
import com.co.kr.modyeo.api.notice.service.NoticeService;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage("공지사항을 찾지 못했습니다")
                        .errorCode("NOT_FOUND_NOTICE")
                        .status(HttpStatus.NOT_FOUND)
                        .build());
        return NoticeDetail.toDto(notice);
    }

    @Override
    public Long updateNotice(NoticeUpdateRequest noticeUpdateRequest) {
        Notice notice = noticeRepository.findById(noticeUpdateRequest.getId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage("공지사항을 찾지 못했습니다")
                        .errorCode("NOT_FOUND_NOTICE")
                        .status(HttpStatus.NOT_FOUND)
                        .build());

        Notice.updateBuilder()
                .notice(notice)
                .title(noticeUpdateRequest.getTitle())
                .content(noticeUpdateRequest.getContent())
                .imagePath(noticeUpdateRequest.getImagePath())
                .useYn(noticeUpdateRequest.getUseYn())
                .build();

        return notice.getId();
    }
}
