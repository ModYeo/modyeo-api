package com.co.kr.modyeo.api.member.friend.repository.custom;

import com.co.kr.modyeo.api.member.friend.domain.entity.Friend;

import java.util.List;

public interface FriendCustomRepository {
    List<Friend> findApprovedByMemberId(Long memberId);

    List<Friend> findReceivedByMemberId(Long memberId);

    List<Friend> findSendByMemberId(Long memberId);
}
