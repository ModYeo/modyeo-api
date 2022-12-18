package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ArticleDetail {

    private Long articleId;

    private String filePath;

    private String title;

    private String content;

    private Yn isHidden;

    private Long hitCount;

    private Integer recommendCount;

    private Long createdBy;

    private Member member;

    private CategoryResponse category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    private List<ReplyResponse> replyResponses = new ArrayList<>();

    @Builder(builderMethodName = "of", builderClassName = "of")
    public ArticleDetail(Long articleId, String filePath, String title, String content, Yn isHidden, Long hitCount, Integer recommendCount, Long createdBy, CategoryResponse category, LocalDateTime createdTime, List<ReplyResponse> replyResponses) {
        this.articleId = articleId;
        this.filePath = filePath;
        this.title = title;
        this.content = content;
        this.isHidden = isHidden;
        this.hitCount = hitCount;
        this.recommendCount = recommendCount;
        this.createdBy = createdBy;
        this.category = category;
        this.createdTime = createdTime;
        this.replyResponses = replyResponses;
    }

    public static ArticleDetail toDto(Article article) {
        return ArticleDetail.of()
                .articleId(article.getId())
                .title(article.getTitle())
                .category(CategoryResponse.toDto(article.getCategory()))
                .filePath(article.getFilePath())
                .content(article.getContent())
                .isHidden(article.getIsHidden())
                .hitCount(article.getHitCount())
                .recommendCount((int) article.getArticleRecommendList().stream().filter(articleRecommend -> articleRecommend.getRecommendYn() == Yn.Y).count())
                .createdBy(article.getCreatedBy())
                .createdTime(article.getCreatedDate())
                .replyResponses(article.getReplyList().stream().map(ReplyResponse::toDto).collect(Collectors.toList()))
                .build();
    }

    @Getter
    @Setter
    public static class Member{
        private Long memberId;

        private String email;

        private String nickname;

        @Builder(builderMethodName = "of",builderClassName = "of")
        public Member(Long memberId, String email, String nickname) {
            this.memberId = memberId;
            this.email = email;
            this.nickname = nickname;
        }

        public static Member toDto(com.co.kr.modyeo.api.member.domain.entity.Member member) {
            return Member.of()
                    .memberId(member.getId())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .build();
        }
    }
}
