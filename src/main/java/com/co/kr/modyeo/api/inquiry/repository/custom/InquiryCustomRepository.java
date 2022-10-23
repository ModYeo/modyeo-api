package com.co.kr.modyeo.api.inquiry.repository.custom;

import com.co.kr.modyeo.api.inquiry.domain.dto.Request.InquiryRequest;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;

import java.util.List;

public interface InquiryCustomRepository {
    //전체 문의사항
    List<Inquiry> getInquiries(InquiryRequest inquiryRequest);

    //나의 문의사항
    List<Inquiry> findInquiryByEmail(String email);
}