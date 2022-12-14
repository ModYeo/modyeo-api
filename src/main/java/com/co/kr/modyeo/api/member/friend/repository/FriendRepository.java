package com.co.kr.modyeo.api.member.friend.repository;

import com.co.kr.modyeo.api.member.friend.domain.entity.Friend;
import com.co.kr.modyeo.api.member.friend.enumerate.FriendStatus;
import com.co.kr.modyeo.api.member.friend.repository.custom.FriendCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long>, FriendCustomRepository {
}
