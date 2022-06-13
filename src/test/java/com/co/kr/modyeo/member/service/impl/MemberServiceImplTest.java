package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.member.domain.dto.response.MemberResponse;
import com.co.kr.modyeo.member.domain.entity.Member;
import com.co.kr.modyeo.member.repository.MemberRepository;
import com.co.kr.modyeo.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    void getMembers(){
        List<MemberResponse> members = memberService.getMembers();

        assertThat(members.size()).isEqualTo(1);
    }

}