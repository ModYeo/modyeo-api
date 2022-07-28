package com.co.kr.modyeo.api.bbs.repository;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.repository.custom.TeamArticleCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamArticleRepository extends JpaRepository<TeamArticle, Long>, TeamArticleCustomRepository {
}
