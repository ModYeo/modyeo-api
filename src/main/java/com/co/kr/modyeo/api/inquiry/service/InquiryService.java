package com.co.kr.modyeo.api.inquiry.service;

import com.co.kr.modyeo.api.inquiry.domain.dto.request.*;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.AnswerDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryResponse;
import com.co.kr.modyeo.api.inquiry.domain.dto.search.InquirySearch;
import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import org.springframework.data.domain.Slice;

public interface InquiryService {
    Slice<InquiryResponse> getInquiryIndexPage(InquirySearch inquirySearch);
    Slice<InquiryResponse> getSelectedInquiries(InquirySearch inquirySearch);
    InquiryDetail getInquiryDetail(Long id);
    Inquiry createInquiry(InquiryCreateRequest InquiryCreateRequest);
    Inquiry updateInquiry(InquiryUpdateRequest InquiryUpdateRequest);
    void deleteInquiry(Long id);
    AnswerDetail getAnswer(Long id);
    Answer createAnswer(AnswerCreateRequest answerRequest);
    Answer updateAnswer(AnswerUpdateRequest answerRequest);
    void deleteAnswer(Long id);
}
