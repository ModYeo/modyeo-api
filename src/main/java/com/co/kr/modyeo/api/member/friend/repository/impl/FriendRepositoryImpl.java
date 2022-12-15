package com.co.kr.modyeo.api.member.friend.repository.impl;

import com.co.kr.modyeo.api.member.domain.entity.QMember;
import com.co.kr.modyeo.api.member.friend.domain.entity.Friend;
import com.co.kr.modyeo.api.member.friend.enumerate.FriendStatus;
import com.co.kr.modyeo.api.member.friend.repository.custom.FriendCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.member.friend.domain.entity.QFriend.friend;

public class FriendRepositoryImpl extends Querydsl4RepositorySupport implements FriendCustomRepository {
    public FriendRepositoryImpl() {
        super(Friend.class);
    }


    @Override
    public List<Friend> findApprovedByMemberId(Long memberId) {
        QMember receiveMember = new QMember("receiveMEmber");
        QMember sendMember = new QMember("sendMember");

        return select(friend)
                .from(friend)
                .innerJoin(friend.receiveMember, receiveMember)
                .innerJoin(friend.sendMember, sendMember)
                .where(memberIdEq(receiveMember, memberId)
                        .or(memberIdEq(sendMember, memberId))
                        .and(friend.friendStatus.eq(FriendStatus.APPROVED)))
                .fetch();
    }

    @Override
    public List<Friend> findReceivedByMemberId(Long memberId) {
        return select(friend)
                .from(friend)
                .innerJoin(friend.receiveMember, member)
                .where(memberIdEq(member, memberId).and(friend.friendStatus.eq(FriendStatus.SUBMITTED)))
                .fetch();
    }

    @Override
    public List<Friend> findSendByMemberId(Long memberId) {
        return select(friend)
                .from(friend)
                .innerJoin(friend.sendMember, member)
                .where(memberIdEq(member, memberId).and(friend.friendStatus.eq(FriendStatus.SUBMITTED)))
                .fetch();
    }

    private BooleanExpression memberIdEq(QMember member, Long memberId) {
        return memberId != null ? member.id.eq(memberId) : null; }
    }
