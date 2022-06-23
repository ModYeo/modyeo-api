package com.co.kr.modyeo.member.domain.entity.link;

import com.co.kr.modyeo.member.domain.entity.BaseEntity;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.domain.entity.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MEMBER_CATEGORY")
public class MemberCategory extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_category")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
