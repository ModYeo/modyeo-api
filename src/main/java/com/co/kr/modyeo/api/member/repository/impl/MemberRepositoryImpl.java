package com.co.kr.modyeo.api.member.repository.impl;

import com.co.kr.modyeo.api.member.domain.dto.response.MemberDetail;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.custom.MemberCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;

public class MemberRepositoryImpl extends Querydsl4RepositorySupport implements MemberCustomRepository {

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public MemberDetail getMemberDetail(Long memberId) {
        return null;
    }
}
