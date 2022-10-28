package com.co.kr.modyeo.api.inquiry.service.impl;

import com.co.kr.modyeo.api.inquiry.domain.dto.Request.AnswerRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.Request.InquiryRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryResponse;
import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.inquiry.repository.AnswerRepository;
import com.co.kr.modyeo.api.inquiry.repository.InquiryRepository;
import com.co.kr.modyeo.api.inquiry.service.InquiryService;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.BoardErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //TODO : 어노테이션, 무슨 의미인지 알아보기
public class InquiryServiceImpl implements InquiryService {

    private InquiryRepository inquiryRepository;

    private AnswerRepository answerRepository;

    //질문 리스트 조회
    @Override
    public List<InquiryResponse> getInquiries(InquiryRequest inquiryRequest) {
        return inquiryRepository.getInquiries(inquiryRequest)
                .stream().map(InquiryResponse::toDto)
                .collect(Collectors.toList()); //TODO : 구문이해 필요
    }

    //상세조회
    @Override
    public InquiryDetail getInquiryDetail(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage()) // TODO : Article -> inquiry 변경 필
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return InquiryDetail.toDto(inquiry);
    }

    //질의생성
    @Override
    public Inquiry createInquiry(InquiryRequest inquiryRequest) {
        return inquiryRepository.save(InquiryRequest.createInquiry(inquiryRequest));
    }

    @Override
    public Inquiry updateInquiry(InquiryRequest inquiryRequest) {
        Inquiry inquiry = inquiryRepository.findById(inquiryRequest.getId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        inquiry.updateInquiryBuilder()
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .build();

        return inquiry;
    }

    @Override
    public void deleteInquiry(Long inquryId) {
        Inquiry inquiry = inquiryRepository.findById(inquryId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        inquiryRepository.delete(inquiry);
    }

    @Override
    public Answer craeteAnswer(AnswerRequest answerRequest) {
        Inquiry inquiry = inquiryRepository.findById(answerRequest.getInquiryId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );

        return answerRepository.save(AnswerRequest.createAnswer(answerRequest, inquiry));
    }

    @Override
    public Answer updateAnswer(AnswerRequest answerRequest) {
        Answer answer = answerRepository.findById(answerRequest.getAnswerId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );
        //answer.changeAnswer(answerRequest.getContent());
        answer.updateAnswerBuilder()
                .content(answerRequest.getContent()).build();
        return answer;
    }

    @Override
    public void deleteAnswer(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );

        answerRepository.deleteById(id);
    }
}
