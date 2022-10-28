package com.co.kr.modyeo.api.inquiry.service;

import com.co.kr.modyeo.api.inquiry.domain.dto.Request.AnswerRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.Request.InquiryRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryResponse;
import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;

import java.util.List;

public interface InquiryService {
    List<InquiryResponse> getInquiries(InquiryRequest inquiryRequest);

    InquiryDetail getInquiryDetail(Long id);

    Inquiry createInquiry(InquiryRequest inquiryRequest);

    Inquiry updateInquiry(InquiryRequest inquiryRequest);

    void deleteInquiry(Long id);

    Answer craeteAnswer(AnswerRequest answerRequest);

    Answer updateAnswer(AnswerRequest answerRequest);

    void deleteAnswer(Long id);
}
