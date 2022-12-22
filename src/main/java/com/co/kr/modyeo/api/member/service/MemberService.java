package com.co.kr.modyeo.api.member.service;

import com.co.kr.modyeo.api.member.domain.dto.request.MemberCategoryRequest;
import com.co.kr.modyeo.api.member.domain.dto.request.MemberProfilePathRequest;
import com.co.kr.modyeo.api.member.domain.dto.request.NicknameUpdateRequest;
import com.co.kr.modyeo.api.member.domain.dto.response.ApplicationMemberDetail;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberDetail;

public interface MemberService {

    Long createMemberInfo(MemberCategoryRequest memberCategoryRequest);

    MemberDetail getMember(Long memberId);

    Long updateNickname(NicknameUpdateRequest nicknameUpdateRequest);

    Long putProfilePath(MemberProfilePathRequest memberProfilePathRequest);

    ApplicationMemberDetail getTeamApplicationMember(Long memberId, Long teamId);

    boolean checkOverlapNickname(String nickname);
}
