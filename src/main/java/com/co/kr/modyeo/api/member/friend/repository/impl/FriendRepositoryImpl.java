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
    public FriendRepositoryImpl(Class<?> domainClass) {
        super(Friend.class);
    }


    @Override
    public List<Friend> findApprovedByEmail(String email) {
        QMember receiveMember = new QMember("receiveMEmber");
        QMember sendMember = new QMember("sendMember");

        return select(friend)
                .from(friend)
                .innerJoin(friend.receiveMember, receiveMember)
                .innerJoin(friend.sendMember, sendMember)
                .where(memberEmailEq(receiveMember, email)
                        .or(memberEmailEq(sendMember, email))
                        .and(friend.friendStatus.eq(FriendStatus.APPROVED)))
                .fetch();
    }

    @Override
    public List<Friend> findReceivedByEmail(String email) {
        return select(friend)
                .from(friend)
                .innerJoin(friend.receiveMember, member)
                .where(memberEmailEq(member, email).and(friend.friendStatus.eq(FriendStatus.SUBMITTED)))
                .fetch();
    }

    @Override
    public List<Friend> findSendByEmail(String email) {
        return select(friend)
                .from(friend)
                .innerJoin(friend.sendMember, member)
                .where(memberEmailEq(member, email).and(friend.friendStatus.eq(FriendStatus.SUBMITTED)))
                .fetch();
    }

    private BooleanExpression memberEmailEq(QMember member, String email) {
        return email != null ? member.email.eq(email) : null; }
    }
