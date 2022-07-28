package com.co.kr.modyeo.api.member.domain.entity.link;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MEMBER_CATEGORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public MemberCategory(Long id, Member member, Category category) {
        this.id = id;
        this.member = member;
        this.category = category;
    }

    @Builder(builderClassName = "createMemberCategoryBuilder", builderMethodName = "createMemberCategoryBuilder")
    public static MemberCategory createMemberCategory(Member member, Category category) {
        return of()
                .category(category)
                .member(member)
                .build();
    }
}
