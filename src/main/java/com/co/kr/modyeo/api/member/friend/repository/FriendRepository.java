package com.co.kr.modyeo.api.member.friend.repository;

import com.co.kr.modyeo.api.member.friend.domain.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
}
