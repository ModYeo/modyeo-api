package com.co.kr.modyeo.api.inquiry.domain.entity;

import com.co.kr.modyeo.api.inquiry.domain.enumerate.InquiryStatus;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "INQUIRY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long id;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    @Column(name = "inquiry_title")
    private String title;

    @Column(name = "inquiry_content")
    @Lob
    private String content; //TODO : MEMORY 계산 후 물리타입 결정하기.

    @Enumerated(value = EnumType.STRING)
    @Column(name = "inquiry_status")
    private InquiryStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;

    //종길's honey tips
    //전체생성자에는 "of" 붙여줌
    //필요에 따라 쓰는 생성자는 "create****" 으로 함.
    //Inquiry.of()
    @Builder(builderClassName = "of", builderMethodName = "of")
    public Inquiry(Long id, String title, String content, InquiryStatus status, Authority authority) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.authority = authority;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Inquiry(String title, String content, Authority authority) {
        this.title = title;
        this.content = content;
        this.authority = authority; // TODO : SecurityUtil 의 checkAuthority 로 해결.
        this.status = (authority == Authority.ROLE_USER) ? InquiryStatus.WAITING : InquiryStatus.FREQUENT;
        //TODO : 유저와 관리자 구분을 위해 validate() 를 만들고 그 안에서 pathVariable 로 쏴주어 판단할 것. user만 튕겨내기.
    }

    @Builder(builderClassName = "updateInquiryBuilder", builderMethodName = "updateInquiryBuilder")
    public void updateInquiry(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
