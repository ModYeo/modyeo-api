package com.co.kr.modyeo.api.member.friend.service.impl;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.friend.domain.entity.Friend;
import com.co.kr.modyeo.api.member.friend.enumerate.FriendStatus;
import com.co.kr.modyeo.api.member.friend.repository.FriendRepository;
import com.co.kr.modyeo.api.member.friend.service.FriendService;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.FriendErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    @Override
    public void sendFriendRequest(Long receiverId) {
        String sendMemberUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Member sendMember = memberRepository.findByEmail(sendMemberUsername)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(FriendErrorCode.SENDER_NOT_FOUND.getMessage())
                        .errorCode(FriendErrorCode.SENDER_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Member receiveMember = memberRepository.findById(receiverId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Friend friend = Friend.builder()
                .sendMember(sendMember)
                .receiveMember(receiveMember)
                .friendStatus(FriendStatus.SUBMITTED)
                .build();

        friendRepository.save(friend);
    }
}
