package com.co.kr.modyeo.api.inquiry.service.impl;

import com.co.kr.modyeo.api.inquiry.domain.dto.request.*;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.AnswerDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryResponse;
import com.co.kr.modyeo.api.inquiry.domain.dto.search.InquirySearch;
import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.inquiry.domain.enumerate.InquiryStatus;
import com.co.kr.modyeo.api.inquiry.repository.AnswerRepository;
import com.co.kr.modyeo.api.inquiry.repository.InquiryRepository;
import com.co.kr.modyeo.api.inquiry.service.InquiryService;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.InquiryErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

    private final AnswerRepository answerRepository;

    @Override
    public Slice<InquiryResponse> getInquiryIndexPage(InquirySearch inquirySearch) {
        PageRequest pageRequest = PageRequest.of(inquirySearch.getOffset(), inquirySearch.getLimit(),
                                                 inquirySearch.getDirection(), inquirySearch.getOrderBy());
        return inquiryRepository.getInquiryIndexPage(inquirySearch, pageRequest).map(InquiryResponse::toDto);
    }

    //질문 리스트 조회
    @Override
    public Slice<InquiryResponse> getSelectedInquiries(InquirySearch inquirySearch) {
        PageRequest pageRequest = PageRequest.of(inquirySearch.getOffset(), inquirySearch.getLimit(),
                                                 inquirySearch.getDirection(), inquirySearch.getOrderBy());
        return inquiryRepository.getSelectedInquiries(inquirySearch, pageRequest).map(InquiryResponse::toDto);
    }

    //상세조회
    @Override
    public InquiryDetail getInquiryDetail(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(InquiryErrorCode.NOT_FOUND_INQUIRY.getMessage())
                        .errorCode(InquiryErrorCode.NOT_FOUND_INQUIRY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return InquiryDetail.toDto(inquiry);
    }

    //질의생성
    @Override
    @Transactional
    public Inquiry createInquiry(InquiryCreateRequest inquiryCreateRequest) {
        return inquiryRepository.save(inquiryCreateRequest.createInquiry(inquiryCreateRequest));
    }

    @Override
    @Transactional
    public Inquiry updateInquiry(InquiryUpdateRequest inquiryUpdateRequest) {
        Inquiry inquiry = inquiryRepository.findById(inquiryUpdateRequest.getId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(InquiryErrorCode.NOT_FOUND_INQUIRY.getMessage())
                        .errorCode(InquiryErrorCode.NOT_FOUND_INQUIRY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        inquiry.updateInquiryBuilder()
                .title(inquiryUpdateRequest.getTitle())
                .content(inquiryUpdateRequest.getContent())
                .isHidden(inquiryUpdateRequest.getIsHidden())
                .build();

        return inquiry;
    }

    @Override
    public void deleteInquiry(Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(InquiryErrorCode.NOT_FOUND_INQUIRY.getMessage())
                        .errorCode(InquiryErrorCode.NOT_FOUND_INQUIRY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        inquiryRepository.delete(inquiry);
    }

    @Override
    public AnswerDetail getAnswer(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(InquiryErrorCode.NOT_FOUND_ANSWER.getMessage())
                        .errorCode(InquiryErrorCode.NOT_FOUND_ANSWER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );
        return AnswerDetail.toDto(answer);
    }

    @Override
    @Transactional
    public Answer createAnswer(AnswerCreateRequest answerCreateRequest) {
        Inquiry inquiry = inquiryRepository.findById(answerCreateRequest.getInquiryId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(InquiryErrorCode.NOT_FOUND_INQUIRY.getMessage())
                        .errorCode(InquiryErrorCode.NOT_FOUND_INQUIRY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );

        if (inquiry.getStatus()==InquiryStatus.WAITING) {
            inquiry.updateInquiryStatusBuilder().status(InquiryStatus.COMPLETE).build();
        }

        Answer savedAnswer = answerRepository.save(AnswerCreateRequest.createAnswer(answerCreateRequest, inquiry));
        inquiry.getAnswerList().add(savedAnswer);
        return savedAnswer;
    }

    @Override
    @Transactional
    public Answer updateAnswer(AnswerUpdateRequest answerUpdateRequest) {
        Answer answer = answerRepository.findById(answerUpdateRequest.getAnswerId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(InquiryErrorCode.NOT_FOUND_ANSWER.getMessage())
                        .errorCode(InquiryErrorCode.NOT_FOUND_ANSWER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );
        //answer.changeAnswer(answerRequest.getContent());
        answer.updateAnswerBuilder()
                .content(answerUpdateRequest.getContent()).build();
        return answer;
    }

    @Override
    public void deleteAnswer(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(InquiryErrorCode.NOT_FOUND_ANSWER.getMessage())
                        .errorCode(InquiryErrorCode.NOT_FOUND_ANSWER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );

        answerRepository.delete(answer);
    }
}
