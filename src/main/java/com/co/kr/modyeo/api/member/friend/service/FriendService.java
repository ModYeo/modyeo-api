package com.co.kr.modyeo.api.member.friend.service;

import com.co.kr.modyeo.api.member.friend.domain.response.FriendResponse;

import java.util.List;

public interface FriendService {
    void sendFriendRequest(String username, Long receiverId);
    void approveFriendRequest(Long requestId);
    void denyFriendRequest(Long requestId);
    void deleteFriend(Long friendId);

    List<FriendResponse> getApprovedFriends(String email);
    List<FriendResponse> getReceiveFriendRequests(String email);
    List<FriendResponse> getSendFriendRequests(String email);

    void blockFriendRequest(Long friendId);
}
