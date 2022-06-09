package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.member.repository.MemberRepository;
import com.co.kr.modyeo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

}
