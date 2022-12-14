package com.co.kr.modyeo.api.member.repository.custom.impl;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.custom.MemberCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Optional;

import static com.co.kr.modyeo.api.member.collection.domain.entity.QCollectionInfo.collectionInfo;
import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.member.domain.entity.link.QMemberCategory.memberCategory;
import static com.co.kr.modyeo.api.member.domain.entity.link.QMemberCollectionInfo.memberCollectionInfo;
import static com.co.kr.modyeo.api.team.domain.entity.link.QCrew.crew;

public class MemberRepositoryImpl extends Querydsl4RepositorySupport implements MemberCustomRepository {

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public Optional<Member> getMember(Long memberId) {
        return Optional.ofNullable(select(member)
                .from(member)
                .leftJoin(member.teamList, crew)
                .leftJoin(member.interestCategoryList, memberCategory)
                .leftJoin(member.memberCollectionInfoList, memberCollectionInfo)
                .fetchJoin()
                .where(memberIdEq(memberId))
                .fetchOne());

    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? member.id.eq(memberId) : null;
    }

}
