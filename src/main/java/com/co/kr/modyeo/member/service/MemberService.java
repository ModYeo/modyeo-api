package com.co.kr.modyeo.member.service;

import com.co.kr.modyeo.member.domain.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {
    List<MemberResponse> getMembers();
}
