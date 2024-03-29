package com.co.kr.modyeo.api.member.friend.service.impl;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.friend.domain.entity.Friend;
import com.co.kr.modyeo.api.member.friend.domain.response.FriendResponse;
import com.co.kr.modyeo.api.member.friend.enumerate.FriendStatus;
import com.co.kr.modyeo.api.member.friend.repository.FriendRepository;
import com.co.kr.modyeo.api.member.friend.service.FriendService;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.FriendErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    @Transactional
    @Override
    public void sendFriendRequest(Long memberId, Long receiverId) {
        Member sendMember = memberRepository.findById(memberId)
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

    @Transactional
    @Override
    public void approveFriendRequest(Long requestId) {
        Friend friend = friendRepository.findById(requestId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(FriendErrorCode.FRIEND_NOT_FOUND.getMessage())
                        .errorCode(FriendErrorCode.FRIEND_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        friend.approveFriend();
    }

    @Transactional
    @Override
    public void denyFriendRequest(Long requestId) {
        Friend friend = friendRepository.findById(requestId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(FriendErrorCode.FRIEND_NOT_FOUND.getMessage())
                        .errorCode(FriendErrorCode.FRIEND_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        friend.denyFriend();
    }

    @Transactional
    @Override
    public void deleteFriend(Long friendId) {
        Friend friend = friendRepository.findById(friendId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(FriendErrorCode.FRIEND_NOT_FOUND.getMessage())
                        .errorCode(FriendErrorCode.FRIEND_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        friend.deleteFriend();
    }

    @Override
    public List<FriendResponse> getApprovedFriends(Long memberId) {
        List<Friend> friendList = friendRepository.findApprovedByMemberId(memberId);

        return friendList.stream()
                .map(FriendResponse::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendResponse> getReceiveFriendRequests(Long memberId) {
        List<Friend> friendList = friendRepository.findReceivedByMemberId(memberId);

        return friendList.stream()
                .map(FriendResponse::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendResponse> getSendFriendRequests(Long memberId) {
        List<Friend> friendList = friendRepository.findSendByMemberId(memberId);

        return friendList.stream()
                .map(FriendResponse::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void blockFriendRequest(Long friendId) {
        Friend friend = friendRepository.findById(friendId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(FriendErrorCode.FRIEND_NOT_FOUND.getMessage())
                        .errorCode(FriendErrorCode.FRIEND_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
        friend.blockFriend();
    }
}
