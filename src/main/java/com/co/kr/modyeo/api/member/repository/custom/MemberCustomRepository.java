package com.co.kr.modyeo.api.member.repository.custom;

import com.co.kr.modyeo.api.member.domain.dto.request.MemberSearch;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberResponse;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface MemberCustomRepository {
    Optional<Member> getMember(Long memberId);

    Slice<MemberResponse> searchMember(MemberSearch memberSearch, PageRequest pageRequest);
}
