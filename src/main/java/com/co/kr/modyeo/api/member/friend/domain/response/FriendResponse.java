package com.co.kr.modyeo.api.member.friend.domain.response;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.friend.domain.entity.Friend;
import com.co.kr.modyeo.api.member.friend.enumerate.FriendStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class FriendResponse {
    private Long id;
    private Long sendMember;
    private Long receiveMember;
    private FriendStatus friendStatus;
    private Long createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;
    private Long updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedTime;

    @Builder
    public FriendResponse(Long id, Long sendMember, Long receiveMember, FriendStatus friendStatus, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime) {
        this.id = id;
        this.sendMember = sendMember;
        this.receiveMember = receiveMember;
        this.friendStatus = friendStatus;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
    }

    public static FriendResponse toResponse(Friend friend) {
        return FriendResponse.builder()
                .id(friend.getId())
                .sendMember(friend.getSendMember().getId())
                .receiveMember(friend.getReceiveMember().getId())
                .friendStatus(friend.getFriendStatus())
                .createdBy(friend.getCreatedBy())
                .createdTime(friend.getCreatedDate())
                .updatedBy(friend.getUpdatedBy())
                .updatedTime(friend.getLastModifiedDate())
                .build();
    }
}
