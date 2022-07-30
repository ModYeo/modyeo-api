package com.co.kr.modyeo.api.member.domain.entity.link;

import com.co.kr.modyeo.api.member.collection.domain.entity.CollectionInfo;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "MEMBER_COLLECTION_INFO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCollectionInfo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_collection_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_info_id")
    private CollectionInfo collectionInfo;

    @Column(name = "agree_yn")
    private Yn agreeYn;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public MemberCollectionInfo(Long id, Member member, CollectionInfo collectionInfo, Yn agreeYn) {
        this.id = id;
        this.member = member;
        this.collectionInfo = collectionInfo;
        this.agreeYn = agreeYn;
    }

    @Builder(builderClassName = "createMemberCollectionInfoBuilder",builderMethodName = "createMemberCollectionInfoBuilder")
    public MemberCollectionInfo(Member member, CollectionInfo collectionInfo, Yn agreeYn) {
        this.member = member;
        this.collectionInfo = collectionInfo;
        this.agreeYn = agreeYn;
    }
}
