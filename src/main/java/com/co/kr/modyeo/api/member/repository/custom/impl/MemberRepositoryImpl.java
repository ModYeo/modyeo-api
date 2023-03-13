package com.co.kr.modyeo.api.member.repository.custom.impl;

import com.co.kr.modyeo.api.member.domain.dto.request.MemberSearch;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberResponse;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.custom.MemberCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.member.domain.entity.link.QMemberActiveArea.memberActiveArea;
import static com.co.kr.modyeo.api.member.domain.entity.link.QMemberCategory.memberCategory;
import static com.co.kr.modyeo.api.member.domain.entity.link.QMemberCollectionInfo.memberCollectionInfo;
import static com.co.kr.modyeo.api.team.domain.entity.link.QCrew.crew;
import static com.co.kr.modyeo.api.team.domain.entity.link.QMemberTeam.memberTeam;

public class MemberRepositoryImpl extends Querydsl4RepositorySupport implements MemberCustomRepository {

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public Optional<Member> getMember(Long memberId) {
        return Optional.ofNullable(select(member)
                .from(member)
                .leftJoin(member.teamList, crew)
                .innerJoin(member.interestCategoryList, memberCategory)
                .innerJoin(member.memberCollectionInfoList, memberCollectionInfo)
                .innerJoin(member.memberActiveAreaList, memberActiveArea)
                .fetchJoin()
                .where(memberIdEq(memberId))
                .distinct()
                .fetchOne());

    }

    @Override
    public Slice<MemberResponse> searchMember(MemberSearch memberSearch, PageRequest pageRequest) {
        return applySlicing(pageRequest, contentQuery ->
                contentQuery.select(Projections.constructor(MemberResponse.class,
                                member.id,
                                member.nickname,
                                member.email,
                                member.username,
                                member.sex,
                                member.birthDay))
                        .from(member)
                        .where(nicknameLike(memberSearch.getNickname()),
                                usernameEq(memberSearch.getUsername())));
    }

    private BooleanExpression usernameEq(String username) {
        return StringUtils.hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression nicknameLike(String nickname) {
        return StringUtils.hasText(nickname) ? member.nickname.contains(nickname) : null;
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? member.id.eq(memberId) : null;
    }

}
