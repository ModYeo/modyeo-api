package com.co.kr.modyeo.api.inquiry.domain.entity;

import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.util.SecurityUtil;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ANSWER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @Column(name = "answer_content")
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public Answer(Long id, Inquiry inquiry, String content, Authority authority) {
        this.id = id;
        this.inquiry = inquiry;
        this.content = content;
        this.authority = authority;
    }

    @Builder(builderClassName = "createAnswerBuilder", builderMethodName = "createAnswerBuilder")
    public static Answer createAnswer(String content, Inquiry inquiry) {
        return of()
                .content(content)
                .authority(SecurityUtil.checkAuthority())
                .inquiry(inquiry)
                .build();
    }

    @Builder(builderClassName = "updateAnswerBuilder", builderMethodName = "updateAnswerBuilder")
    public void changeAnswer(String content) {
        this.content = content;
    }
}
