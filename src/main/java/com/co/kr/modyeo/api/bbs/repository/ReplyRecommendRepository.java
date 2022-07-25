package com.co.kr.modyeo.api.bbs.repository;

import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import com.co.kr.modyeo.api.bbs.domain.entity.link.ArticleRecommend;
import com.co.kr.modyeo.api.bbs.domain.entity.link.ReplyRecommend;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRecommendRepository extends JpaRepository<ReplyRecommend,Long> {
    ReplyRecommend findByMemberAndReply(Member member, Reply reply);
}
