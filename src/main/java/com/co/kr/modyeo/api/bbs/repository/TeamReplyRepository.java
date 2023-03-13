package com.co.kr.modyeo.api.bbs.repository;

import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.co.kr.modyeo.api.bbs.repository.custom.TeamReplyCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamReplyRepository extends JpaRepository<TeamReply, Long>, TeamReplyCustomRepository {
    List<TeamReply> findByReplyGroup(Long ReplyGroup);
}
