package com.co.kr.modyeo.api.member.service;

import com.co.kr.modyeo.api.member.domain.dto.request.*;
import com.co.kr.modyeo.api.member.domain.dto.response.ApplicationMemberDetail;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberDetail;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberResponse;
import org.springframework.data.domain.Slice;

public interface MemberService {

    Long createMemberInfo(MemberCategoryRequest memberCategoryRequest);

    MemberDetail getMember(Long memberId);

    Long updateNickname(NicknameUpdateRequest nicknameUpdateRequest);

    Long putProfilePath(MemberProfilePathRequest memberProfilePathRequest);

    ApplicationMemberDetail getTeamApplicationMember(Long memberId, Long teamId);

    Slice<MemberResponse> getMembers(MemberSearch memberSearch);

    Long createMemberActiveArea(MemberActiveAreaRequest memberActiveAreaRequest);
}
