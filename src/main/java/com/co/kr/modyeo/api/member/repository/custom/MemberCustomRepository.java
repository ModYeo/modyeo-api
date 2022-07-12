package com.co.kr.modyeo.api.member.repository.custom;

import com.co.kr.modyeo.api.member.domain.dto.response.MemberDetail;

public interface MemberCustomRepository {
    MemberDetail getMemberDetail(Long memberId);
}
