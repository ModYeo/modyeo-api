package com.co.kr.modyeo.api.inquiry.repository.impl;

import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.repository.custom.AnswerCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.co.kr.modyeo.api.inquiry.domain.entity.QAnswer.answer;
import static com.co.kr.modyeo.api.inquiry.domain.entity.QInquiry.inquiry;


public class AnswerRepositoryImpl extends Querydsl4RepositorySupport implements AnswerCustomRepository {

    public AnswerRepositoryImpl() {
        super(Answer.class);
    }

    @Override
    /*public Answer findByInquiryIdAndAnswerCreatedTime(Long inquiryId, LocalDateTime findingTime) {*/
    public Answer findByInquiryIdAndAnswerCreatedTime(Answer givenAnswer) {
        return selectFrom(answer)
                .from(answer)
                .innerJoin(answer.inquiry, inquiry)
                .fetchJoin()
                .where(answer.inquiry.id.eq(givenAnswer.getId()),
                        (inquiry.createdDate.eq(givenAnswer.getCreatedDate())))
                .fetchOne();
    }

    @Override
    public List<Answer> findByInquiryIdAnswerList(Long inquiryId, Sort sort) {
        return selectFrom(answer)
                .from(answer)
                .innerJoin(answer.inquiry, inquiry)
                .fetchJoin()
                .where(inquiry.id.eq(inquiryId))
                .fetch();
    }
}