package com.co.kr.modyeo.api.inquiry.repository.custom;

import com.co.kr.modyeo.api.inquiry.domain.dto.search.InquirySearch;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

public interface InquiryCustomRepository {
    Slice<Inquiry> getInquiryIndexPage(InquirySearch inquirySearch, PageRequest pageRequest);
    //전체 문의사항
    Slice<Inquiry> getSelectedInquiries(InquirySearch inquirySearch, PageRequest pageRequest);
}