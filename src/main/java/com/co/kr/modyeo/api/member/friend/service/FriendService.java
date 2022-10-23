package com.co.kr.modyeo.api.member.friend.service;

public interface FriendService {
    void sendFriendRequest(Long receiverId);
    void approveFriendRequest(Long requestId);
    void denyFriendRequest(Long requestId);
    void deleteFriend(Long friendId);
}
