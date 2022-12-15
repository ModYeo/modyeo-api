package com.co.kr.modyeo.api.bbs.repository.custom;

import com.co.kr.modyeo.api.bbs.domain.entity.Reply;

import java.util.List;

public interface ReplyCustomRepository {
    List<Reply> findReplyByEmail(Long memberId);
}
