package com.co.kr.modyeo.api.member.repository.custom;

import com.co.kr.modyeo.api.member.domain.entity.Member;

public interface MemberCustomRepository {
    Member getMember(Long memberId);
}
