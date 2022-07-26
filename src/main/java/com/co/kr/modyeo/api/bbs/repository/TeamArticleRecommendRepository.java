package com.co.kr.modyeo.api.bbs.repository;

import com.co.kr.modyeo.api.bbs.domain.entity.link.ArticleRecommend;
import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamArticleRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamArticleRecommendRepository extends JpaRepository<TeamArticleRecommend,Long> {
}
