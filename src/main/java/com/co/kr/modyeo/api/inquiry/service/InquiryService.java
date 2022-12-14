package com.co.kr.modyeo.api.inquiry.service;

import com.co.kr.modyeo.api.inquiry.domain.dto.request.AnswerRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.request.InquiryRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.AnswerDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryResponse;
import com.co.kr.modyeo.api.inquiry.domain.dto.search.InquirySearch;
import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import org.springframework.data.domain.Slice;

public interface InquiryService {
    Slice<InquiryResponse> getInquiryIndexPage(InquirySearch inquirySearch);
    Slice<InquiryResponse> getAllInquiries(InquirySearch inquirySearch, Authority auth);
    Slice<InquiryResponse> getMyInquiries(InquirySearch inquirySearch);
    InquiryDetail getInquiryDetail(Long id);
    Inquiry createInquiry(InquiryRequest inquiryRequest);
    Inquiry updateInquiry(InquiryRequest inquiryRequest);
    void deleteInquiry(Long id);
    AnswerDetail getAnswer(Long id);
    Answer createAnswer(AnswerRequest answerRequest);
    Answer updateAnswer(AnswerRequest answerRequest);
    void deleteAnswer(Long id);
}
