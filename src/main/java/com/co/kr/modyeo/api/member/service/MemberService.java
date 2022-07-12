package com.co.kr.modyeo.api.member.service;

import com.co.kr.modyeo.api.member.domain.dto.request.MemberRequest;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberDetail;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {

    void createMemberInfo(MemberRequest memberRequest);

    MemberDetail getMember(Long memberId);
}
