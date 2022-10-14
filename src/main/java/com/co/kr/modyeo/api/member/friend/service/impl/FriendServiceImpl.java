package com.co.kr.modyeo.api.member.friend.service.impl;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.friend.service.FriendService;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.FriendErrorCode;
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

    @Override
    public void sendFriendRequest(Long receiverId) {
        String senderUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Member sendMember = memberRepository.findByEmail(senderUsername)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(FriendErrorCode.SENDER_NOT_FOUND.getMessage())
                        .errorCode(FriendErrorCode.SENDER_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

    }
}
