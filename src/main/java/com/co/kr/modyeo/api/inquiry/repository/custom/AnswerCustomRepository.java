package com.co.kr.modyeo.api.inquiry.repository.custom;

import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AnswerCustomRepository {
    //답변 수정 : 답변 수정 시에 어떤 답변을 수정할 지에 대해서는 createdTime 을 통해서만 정확한 답변을 조회할 수 있기 때문에
    //          질의 id와 최초 작성시간을 조건으로 한 쿼리문을 작성해야 한다.
    Answer findByInquiryIdAndAnswerCreatedTime(Answer givenAnswer);

    //답변 조회 : 답변 조회를 위해서 inquiry id 가 필요하다.
    //          답변들은 createdTime 을 기준으로 오름차순(ASC)로 결과반환 되어야 한다.
    List<Answer> findByInquiryIdAnswerList(Long inquiryId, Sort sort);
}
