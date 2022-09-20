package com.co.kr.modyeo.api.member.service;

import com.co.kr.modyeo.api.member.domain.dto.request.MemberCategoryRequest;
import com.co.kr.modyeo.api.member.domain.dto.request.MemberProfilePathRequest;
import com.co.kr.modyeo.api.member.domain.dto.request.NicknameUpdateRequest;
import com.co.kr.modyeo.api.member.domain.dto.response.ApplicationMemberDetail;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberDetail;

public interface MemberService {

    void createMemberInfo(MemberCategoryRequest memberCategoryRequest);

    MemberDetail getMember(Long memberId);

    void updateNickname(NicknameUpdateRequest nicknameUpdateRequest);

    void putProfilePath(MemberProfilePathRequest memberProfilePathRequest);

    ApplicationMemberDetail getTeamApplicationMember(Long memberId, Long teamId);
}
