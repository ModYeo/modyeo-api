package com.co.kr.modyeo.api.inquiry.repository.custom;

import com.co.kr.modyeo.api.inquiry.domain.dto.search.InquirySearch;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

public interface InquiryCustomRepository {
    Slice<Inquiry> getInquiryIndexPage(InquirySearch inquirySearch, PageRequest pageRequest);
    //전체 문의사항
    Slice<Inquiry> getAllInquiries(InquirySearch inquirySearch, PageRequest pageRequest, Authority auth);
    Slice<Inquiry> getMyInquiries(Long userId, PageRequest pageRequest);
}