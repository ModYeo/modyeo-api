package com.co.kr.modyeo.api.bbs.repository;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamReplyRecommend;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamReplyRecommendRepository extends JpaRepository<TeamReplyRecommend, Long> {
    TeamReplyRecommend findByMemberAndTeamReply(Member member, TeamReply teamReply);
}
