package com.co.kr.modyeo.api.bbs.repository;

import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import com.co.kr.modyeo.api.bbs.repository.custom.ReplyCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> , ReplyCustomRepository {
    List<Reply> findByReplyGroup(Long replyGroup);
}
