package com.co.kr.modyeo.api.member.friend.domain.request;

import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.member.friend.domain.entity.Friend;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendCreateRequest {
    private Long receiveMember;
}
