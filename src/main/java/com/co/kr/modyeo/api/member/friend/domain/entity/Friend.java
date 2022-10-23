package com.co.kr.modyeo.api.member.friend.domain.entity;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.friend.enumerate.FriendStatus;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "FRIEND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_member_id")
    private Member sendMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_member_id")
    private Member receiveMember;

    @Column(name = "friend_status")
    @Enumerated(value = EnumType.STRING)
    private FriendStatus friendStatus;

    @Builder
    public Friend(Member sendMember, Member receiveMember, FriendStatus friendStatus) {
        this.sendMember = sendMember;
        this.receiveMember = receiveMember;
        this.friendStatus = friendStatus;
    }

    public void approveFriend() {
        this.friendStatus = FriendStatus.APPROVED;
    }

    public void denyFriend() {
        this.friendStatus = FriendStatus.DENIED;
    }

    public void deleteFriend() {
        this.friendStatus = FriendStatus.DELETED;
    }
}
