package com.co.kr.modyeo.api.member.repository.custom;

import com.co.kr.modyeo.api.member.domain.entity.Member;

import java.util.Optional;

public interface MemberCustomRepository {
    Optional<Member> getMember(Long memberId);
}
