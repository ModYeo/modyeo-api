package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ArticleResponse {

    private Long articleId;

    private String title;

    private String content;

    private Long categoryId;

    private String categoryName;

    private String filePath;

    private Yn isHidden;

    private Integer replyCount;

    private Integer recommendCount;

    private Long hitCount;

    private Long createdBy;

    private ArticleResponse.Member member;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public ArticleResponse(Long articleId, String title, String content, Long categoryId, String categoryName, String filePath, Yn isHidden, Integer replyCount, Integer recommendCount, Long hitCount, Long createdBy, Member member, LocalDateTime createdTime) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.replyCount = replyCount;
        this.recommendCount = recommendCount;
        this.hitCount = hitCount;
        this.createdBy = createdBy;
        this.member = member;
        this.createdTime = createdTime;
    }

    public static ArticleResponse toDto(Article article) {
        return ArticleResponse.of()
                .articleId(article.getId())
                .categoryId(article.getCategory().getId())
                .categoryName(article.getCategory().getName())
                .filePath(article.getFilePath())
                .title(article.getTitle())
                .content(article.getContent())
                .isHidden(article.getIsHidden())
                .hitCount(article.getHitCount())
                .recommendCount((int) article.getArticleRecommendList().stream().filter(articleRecommend -> articleRecommend.getRecommendYn() == Yn.Y).count())
                .replyCount(article.getReplyList().size())
                .createdBy(article.getCreatedBy())
                .createdTime(article.getCreatedDate())
                .build();
    }

    @Getter
    @Setter
    public static class Member{
        private Long memberId;

        private String email;

        private String nickname;

        public Member(Long memberId, String email, String nickname) {
            this.memberId = memberId;
            this.email = email;
            this.nickname = nickname;
        }
    }
}
