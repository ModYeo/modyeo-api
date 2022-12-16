package com.co.kr.modyeo.api.inquiry.repository.impl;

import com.co.kr.modyeo.api.inquiry.domain.dto.request.InquiryRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.search.InquirySearch;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.inquiry.domain.enumerate.InquiryStatus;
import com.co.kr.modyeo.api.inquiry.repository.custom.InquiryCustomRepository;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

import static com.co.kr.modyeo.api.bbs.domain.entity.QArticle.article;
import static com.co.kr.modyeo.api.inquiry.domain.entity.QInquiry.inquiry;
import static com.co.kr.modyeo.api.inquiry.domain.entity.QAnswer.answer;

public class InquiryRepositoryImpl extends Querydsl4RepositorySupport implements InquiryCustomRepository {

    public InquiryRepositoryImpl() {
        super(Inquiry.class);
    }

    @Override
    public Slice<Inquiry> getInquiryIndexPage(InquirySearch inquirySearch, PageRequest pageRequest) {
        return applySlicing(pageRequest, contentQuery ->
                    contentQuery.select(inquiry)
                            .from(inquiry)
                            .innerJoin(inquiry, answer.inquiry)
                            .where(
                                    statusCheck(inquirySearch.getStatus())
                                  )
                            );
    }

    @Override
    public Slice<Inquiry> getSelectedInquiries(InquirySearch inquirySearch, PageRequest pageRequest) {
        return applySlicing(pageRequest, contentQuery ->
                contentQuery.select(inquiry)
                        .from(inquiry)
                        .innerJoin(inquiry.answerList, answer)
                        .fetchJoin()
                        .where(
                                inquiryTitleLike(inquirySearch.getTitle()),
                                inquiryContentLike(inquirySearch.getContent()),
                                inquiryCreatedByEq(inquirySearch.getCreatedBy())
                                /*inquiryCategoryIdEq(inquirySearch.getCategoryId()),
                                inquiryBetwCreatedTime(inquirySearch.getFromTime(), inquirySearch.getToTime()),
                                inquiryLtCreatedTime(inquirySearch.getCreatedTime()),
                                inquiryGtCreatedTime(inquirySearch.getCreatedTime()),
                                inquiryEqCreatedTime(inquirySearch.getCreatedTime())
                                TODO:날짜 조건을 어떻게 줄 건지에 대해서 생각홰봐야 함.
                                */
                              )
                        );
    }

    private BooleanExpression statusCheck(InquiryStatus status){
        return status != null ? inquiry.status.eq(status) : null;
    }

    private BooleanExpression adminCheck(Authority auth) {
        if (auth == Authority.ROLE_USER) return null;
        //if (auth == "my") return null;
        return inquiry.authority.eq(auth);
    }

    private BooleanExpression createdByEq(InquiryRequest inquiryRequest) {
        String email = inquiryRequest.getCreatedBy();
        return StringUtils.hasText(email) ? article.createdBy.eq(email) : null;
    }

    private BooleanExpression inquiryTitleLike(String title){
        return title != null ? inquiry.title.contains(title) : null;
    }
    private BooleanExpression inquiryContentLike(String content){
        return content != null ? inquiry.content.contains(content) : null;
    }
    private BooleanExpression inquiryCreatedByEq(String userId){
        return userId != null ? inquiry.createdBy.eq(userId) : null;
    }
    //private BooleanExpression inquiryCategoryIdEq(Catego){return null;} //TODO:향후 질의사항 카테고리 추가 해야 함.
    private BooleanExpression inquiryBetwCreatedTime(LocalDateTime fromTime, LocalDateTime toTime){
        return fromTime != null && toTime != null ? inquiry.createdDate.between(fromTime, toTime) : null;
    }

    private BooleanExpression inquiryLtCreatedTime(LocalDateTime createdTime){
        return createdTime != null ? inquiry.createdDate.lt(createdTime) : null;
    }

    private BooleanExpression inquiryGtCreatedTime(LocalDateTime createdTime) {
        return createdTime != null ? inquiry.createdDate.gt(createdTime) : null;
    }

    private BooleanExpression inquiryEqCreatedTime(LocalDateTime createdTime) {
        return createdTime != null ? inquiry.createdDate.eq(createdTime) : null;
    }
}
