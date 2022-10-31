package com.co.kr.modyeo.api.member.friend.repository.custom;

import com.co.kr.modyeo.api.member.friend.domain.entity.Friend;
import com.co.kr.modyeo.api.member.friend.enumerate.FriendStatus;

import java.util.List;

public interface FriendCustomRepository {
    List<Friend> findApprovedByEmail(String email);

    List<Friend> findReceivedByEmail(String email);

    List<Friend> findSendByEmail(String email);
}
