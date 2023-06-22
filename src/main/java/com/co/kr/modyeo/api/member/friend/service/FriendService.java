package com.co.kr.modyeo.api.member.friend.service;

import com.co.kr.modyeo.api.member.friend.domain.response.FriendResponse;

import java.util.List;

public interface FriendService {
    void sendFriendRequest(Long memberId, Long receiverId);
    void approveFriendRequest(Long requestId);
    void denyFriendRequest(Long requestId);
    void deleteFriend(Long friendId);

    List<FriendResponse> getApprovedFriends(Long memberId);
    List<FriendResponse> getReceiveFriendRequests(Long memberId);
    List<FriendResponse> getSendFriendRequests(Long memberId);
}
