package com.co.kr.modyeo.api.bbs.repository;

import com.co.kr.modyeo.api.bbs.domain.entity.link.ArticleRecommend;
import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamReplyRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamReplyRecommendRepository extends JpaRepository<TeamReplyRecommend,Long> {
}
