package com.co.kr.modyeo.api.member.service;

import com.co.kr.modyeo.api.member.domain.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {
    List<MemberResponse> getMembers();
}
