package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.repository.ArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.ReplyRepository;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.api.member.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    private BoardService boardService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ReplyRepository replyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        boardService = new BoardServiceImpl(articleRepository,replyRepository);
    }

    @Test
    void getArticles() {
        ArticleSearch articleRequest = ArticleSearch.of()
                .title("test")
                .build();

        List<Article> articleList = new ArrayList<>();

        Article article = Article.of()
                .id(1L)
                .title("test")
                .content("test")
                .build();

        articleList.add(article);
        PageRequest pageRequest = PageRequest.of(0,10);
        Slice<Article> articles = new SliceImpl<>(articleList,pageRequest,false);

        given(articleRepository.searchArticle(any(),any())).willReturn(articles);
        Slice<ArticleResponse> articleResponses = boardService.getArticles(articleRequest);

        assertThat(articleResponses.getContent().size()).isEqualTo(1);
    }

    @Test
    void createArticle() {

    }

    @Test
    void getArticle() {
    }
}