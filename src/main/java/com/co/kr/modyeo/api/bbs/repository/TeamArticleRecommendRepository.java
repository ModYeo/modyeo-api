package com.co.kr.modyeo.api.bbs.repository;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.domain.entity.link.ArticleRecommend;
import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamArticleRecommend;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamArticleRecommendRepository extends JpaRepository<TeamArticleRecommend,Long> {
    TeamArticleRecommend findByMemberAndTeamArticle(Member member, TeamArticle teamArticle);
}
