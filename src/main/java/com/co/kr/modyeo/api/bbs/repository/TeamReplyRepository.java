package com.co.kr.modyeo.api.bbs.repository;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamReplyRepository extends JpaRepository<TeamReply,Long> {
    List<TeamReply> findByReplyGroup(Long ReplyGroup);
}
