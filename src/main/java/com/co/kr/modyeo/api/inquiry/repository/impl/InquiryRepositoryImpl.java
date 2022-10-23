package com.co.kr.modyeo.api.inquiry.repository.impl;

import com.co.kr.modyeo.api.inquiry.domain.dto.Request.InquiryRequest;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.inquiry.repository.custom.InquiryCustomRepository;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

import static com.co.kr.modyeo.api.bbs.domain.entity.QArticle.article;
import static com.co.kr.modyeo.api.inquiry.domain.entity.QInquiry.inquiry;

public class InquiryRepositoryImpl extends Querydsl4RepositorySupport implements InquiryCustomRepository {

    public InquiryRepositoryImpl() {
        super(Inquiry.class);
    }

    @Override
    public List<Inquiry> getInquiries(InquiryRequest inquiryRequest) {
        return select(inquiry)
                .from(inquiry)
                .where(adminCheck(inquiryRequest)
                        .or(createdByEq(inquiryRequest))
                )
                .fetchJoin()
                .fetch();
    }

    @Override
    public List<Inquiry> findInquiryByEmail(String email) {
        return select(inquiry)
                .from(inquiry)
                .where(inquiry.createdBy.eq(email))
                .fetchJoin()
                .fetch();
    }

    private BooleanExpression adminCheck(InquiryRequest inquiryRequest) {
        String flag = inquiryRequest.getFlag();
        Authority auth = inquiryRequest.getAuthority();
        if (flag == "my") return null;
        return inquiry.authority.eq(auth);
    }

    private BooleanExpression createdByEq(InquiryRequest inquiryRequest) {
        String flag = inquiryRequest.getFlag();
        String email = inquiryRequest.getCreatedBy();
        return flag == "my" && email != null && !email.equals("") ? article.createdBy.eq(email) : null;
    }

}
