package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.api.member.domain.dto.response.MemberResponse;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.member.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberServiceImplTest {

    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    void getMembers(){
        List<MemberResponse> members = memberService.getMembers();

        assertThat(members.size()).isEqualTo(1);
    }

}