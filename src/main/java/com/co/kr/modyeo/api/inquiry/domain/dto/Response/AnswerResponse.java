package com.co.kr.modyeo.api.inquiry.domain.dto.Response;

import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.inquiry.domain.enumerate.InquiryStatus;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class AnswerResponse {
    private Long id;
    private Inquiry inquiry;
    private String title;
    private String content;
    private List<Answer> answerList;
    private Authority authority;
    private InquiryStatus status;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

}
