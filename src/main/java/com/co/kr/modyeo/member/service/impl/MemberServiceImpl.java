package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.member.domain.dto.response.MemberResponse;
import com.co.kr.modyeo.member.repository.MemberRepository;
import com.co.kr.modyeo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<MemberResponse> getMembers() {
        return memberRepository.findMembers().stream()
                .map(MemberResponse::toRes)
                .collect(Collectors.toList());
    }
}
