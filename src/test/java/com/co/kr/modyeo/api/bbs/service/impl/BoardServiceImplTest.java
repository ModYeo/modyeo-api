package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.ReplyUpdateRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.ReplyCreateRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import com.co.kr.modyeo.api.bbs.repository.ArticleRecommendRepository;
import com.co.kr.modyeo.api.bbs.repository.ArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.ReplyRecommendRepository;
import com.co.kr.modyeo.api.bbs.repository.ReplyRepository;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.BoardErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import com.co.kr.modyeo.common.util.ReflectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    private BoardService boardService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ReplyRepository replyRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ArticleRecommendRepository articleRecommendRepository;

    @Mock
    private ReplyRecommendRepository replyRecommendRepository;

    Category FIXTURE_CAT_01 = Category.of()
            .id(1L)
            .name("테스트 카테고리")
            .imagePath("String")
            .useYn(Yn.Y)
            .build();

    Article FIXTURE_ART_01 = Article.of()
            .id(1L)
            .title("test")
            .content("test")
            .category(FIXTURE_CAT_01)
            .hitCount(1L)
            .replyCount(1)
            .articleRecommendList(new ArrayList<>())
            .build();

    Member FIXTURE_MEM_01 = Member.of()
            .id(1L)
            .nickname("tester")
            .email("test@qweqwe.com")
            .build();

    ReplyResponse FIXTURE_REPLY_RES_01 = ReplyResponse.of()
            .replyId(1L)
            .articleId(1L)
            .member(ReplyResponse.Member.of()
                    .memberId(1L)
                    .nickname("tester")
                    .email("test@qweqwe.com")
                    .build())
            .content("test")
            .deleteYn(Yn.N)
            .build();

    Reply FIXTURE_REPLY_01 = Reply.of()
            .id(1L)
            .article(FIXTURE_ART_01)
            .content("test")
            .replyDepth(1)
            .deleteYn(Yn.N)
            .build();

    Reply FIXTURE_REPLY_02 = Reply.of()
            .id(2L)
            .article(FIXTURE_ART_01)
            .content("test")
            .replyDepth(2)
            .replyGroup(FIXTURE_REPLY_01.getId())
            .deleteYn(Yn.N)
            .build();

    ReplyCreateRequest FIXTURE_REPLY_REQ_01 = ReplyCreateRequest.of()
            .articleId(FIXTURE_ART_01.getId())
            .content("test")
            .replyDepth(1)
            .build();

    ReplyCreateRequest FIXTURE_REPLY_REQ_02 = ReplyCreateRequest.of()
            .articleId(FIXTURE_ART_01.getId())
            .content("test")
            .replyDepth(2)
            .replyGroup(FIXTURE_REPLY_01.getId())
            .build();

    ReplyUpdateRequest FIXTURE_REPLY_UPDATE_REQUEST_01 = ReplyUpdateRequest.of()
            .id(1L)
            .content("test2")
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        boardService = new BoardServiceImpl(articleRepository, replyRepository, categoryRepository, memberRepository, articleRecommendRepository, replyRecommendRepository);
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
        PageRequest pageRequest = PageRequest.of(0, 10);
        Slice<Article> articles = new SliceImpl<>(articleList, pageRequest, false);

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

    @Test
    void getArticleSuccess() {
        given(articleRepository.findArticle(any())).willReturn(Optional.ofNullable(FIXTURE_ART_01));
        given(memberRepository.findById(any())).willReturn(Optional.ofNullable(FIXTURE_MEM_01));
        given(replyRepository.findByArticleId(any())).willReturn(List.of(FIXTURE_REPLY_RES_01));
        ArticleDetail article = boardService.getArticle(1L);

        assertThat(article.getArticleId()).isEqualTo(1L);
        assertThat(article.getReplyResponses().size()).isEqualTo(1);
        assertThat(article.getReplyResponses().get(0).getMember().getNickname()).isEqualTo("tester");
        assertThat(article.getReplyResponses().get(0).getDeleteYn()).isEqualTo(Yn.N);
        assertThat(article.getHitCount()).isEqualTo(2L);
    }

    @Test
    void getArticleNotFound(){
        given(articleRepository.findArticle(any())).willThrow(ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                .status(HttpStatus.BAD_REQUEST)
                .build());

        assertThatThrownBy(() -> {
            boardService.getArticle(1L);
        }).isInstanceOf(Exception.class).hasMessageContaining(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage());
    }


    @Test
    void getArticleNotFoundMember(){
        given(articleRepository.findArticle(any())).willReturn(Optional.ofNullable(FIXTURE_ART_01));
        given(memberRepository.findById(any())).willThrow(ApiException.builder()
                .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                .status(HttpStatus.BAD_REQUEST)
                .build());

        assertThatThrownBy(() -> {
            boardService.getArticle(1L);
        }).isInstanceOf(Exception.class).hasMessageContaining(MemberErrorCode.NOT_FOUND_MEMBER.getMessage());
    }

    @Test
    void createReplyDepth1(){
        given(articleRepository.findById(any())).willReturn(Optional.ofNullable(FIXTURE_ART_01));
        given(replyRepository.save(any())).willReturn(FIXTURE_REPLY_01);

        Long replyId = boardService.createReply(FIXTURE_REPLY_REQ_01);
        assertThat(replyId).isEqualTo(1L);
        assertThat(FIXTURE_ART_01.getReplyCount()).isEqualTo(2);
    }

    @Test
    void createReplyDepth2(){
        given(articleRepository.findById(any())).willReturn(Optional.ofNullable(FIXTURE_ART_01));
        given(replyRepository.save(any())).willReturn(FIXTURE_REPLY_02);

        Long replyId = boardService.createReply(FIXTURE_REPLY_REQ_02);
        assertThat(replyId).isEqualTo(2L);
        assertThat(FIXTURE_ART_01.getReplyCount()).isEqualTo(2);
    }

    @Test
    void updateReply(){
        given(replyRepository.findById(any())).willReturn(Optional.ofNullable(FIXTURE_REPLY_01));
        boardService.updateReply(FIXTURE_REPLY_UPDATE_REQUEST_01);

        assertThat(FIXTURE_REPLY_01.getContent()).isEqualTo(FIXTURE_REPLY_UPDATE_REQUEST_01.getContent());
    }

    @Test
    void updateReplyNoFound(){
        given(replyRepository.findById(any())).willThrow(ApiException.builder()
                .errorMessage(BoardErrorCode.NOT_FOUND_REPLY.getMessage())
                .errorCode(BoardErrorCode.NOT_FOUND_REPLY.getCode())
                .status(HttpStatus.BAD_REQUEST)
                .build());

        assertThatThrownBy(() -> {
            boardService.updateReply(FIXTURE_REPLY_UPDATE_REQUEST_01);
        }).isInstanceOf(Exception.class).hasMessageContaining(BoardErrorCode.NOT_FOUND_REPLY.getMessage());
    }

    @Test
    void deleteReply() throws IllegalAccessException {
        List<Field> allFields = ReflectionUtil.getAllFields(FIXTURE_REPLY_01);

        for (Field allField : allFields) {
            allField.setAccessible(true);
            if (allField.getName().equals("createdBy")){
                allField.set(FIXTURE_REPLY_01,1L);
            }
        }

        given(replyRepository.findById(any())).willReturn(Optional.ofNullable(FIXTURE_REPLY_01));
        boardService.deleteReply(FIXTURE_REPLY_01.getId(), 1L);

        assertThat(FIXTURE_REPLY_01.getDeleteYn()).isEqualTo(Yn.Y);
        assertThat(FIXTURE_ART_01.getReplyCount()).isEqualTo(0);
    }
}