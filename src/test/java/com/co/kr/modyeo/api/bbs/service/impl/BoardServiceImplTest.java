package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleCreateRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleUpdateRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.repository.ArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.ReplyRepository;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.api.category.repository.CategoryRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    private BoardService boardService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ReplyRepository replyRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        //boardService = new BoardServiceImpl(articleRepository,replyRepository,categoryRepository);
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

//        given(articleRepository.searchArticle(any(),any())).willReturn(articles);
//        Slice<ArticleResponse> articleResponses = boardService.getArticles(articleRequest);

//        assertThat(articleResponses.getContent().size()).isEqualTo(1);
    }

//    @Test
//    void createArticle() {
//        ArticleCreateRequest articleCreateRequest = ArticleCreateRequest.of()
//                .title("test title")
//                .content("test contents")
//                .build();
//
//        Article article = Article.of()
//                .id(1L)
//                .title("test title")
//                .content("test contents")
//                .build();
//
//        given(articleRepository.save(any())).willReturn(article);
////        boardService.createArticle(articleCreateRequest);
//
////        then(articleRepository).should().save(any());
//    }

//    @Test
//    void updateArticle(){
//        ArticleUpdateRequest articleCreateRequest = ArticleUpdateRequest.of()
//                .title("test title2")
//                .content("test contents2")
//                .build();
//
//        Article article = Article.of()
//                .id(1L)
//                .title("test title")
//                .content("test contents")
//                .build();
//
//        given(articleRepository.findById(any())).willReturn(Optional.of(article));
//
////        Long id = boardService.updateArticle(articleCreateRequest);
//
//        then(articleRepository).should().findById(any());
//    }

//    @Test
//    void deleteArticle(){
//        Article article = Article.of()
//                .id(1L)
//                .title("test title")
//                .content("test contents")
//                .build();
//
//        given(articleRepository.findById(any())).willReturn(Optional.of(article));
//
//        boardService.deleteArticle(1L);
//        then(articleRepository).should().findById(any());
//        then(articleRepository).should().delete(any());
//    }

    /*@Test
    void updateRecommend(){
        ArticleRecommendRequest articleRecommendRequest = ArticleRecommendRequest.of()
                .articleId(1L)
                .recommendYn(Yn.Y)
                .build();

        Article article = Article.of()
                .id(1L)
                .title("test title")
                .content("test contents")
                .recommendCount(0L)
                .build();

        given(articleRepository.findById(any())).willReturn(Optional.of(article));
        boardService.updateArticleRecommend(articleRecommendRequest);

        then(articleRepository).should().findById(any());

        assertThat(article.getRecommendCount()).isEqualTo(1L);
    }*/

    /*@Test
    void updateRecommendN(){
        ArticleRecommendRequest articleRecommendRequest = ArticleRecommendRequest.of()
                .articleId(1L)
                .recommendYn(Yn.N)
                .build();

        Article article = Article.of()
                .id(1L)
                .title("test title")
                .content("test contents")
                .recommendCount(1L)
                .build();

        given(articleRepository.findById(any())).willReturn(Optional.of(article));
        boardService.updateArticleRecommend(articleRecommendRequest);

        then(articleRepository).should().findById(any());

        assertThat(article.getRecommendCount()).isEqualTo(0L);
    }*/
}