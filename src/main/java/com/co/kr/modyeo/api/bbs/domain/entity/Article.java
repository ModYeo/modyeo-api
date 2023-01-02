package com.co.kr.modyeo.api.bbs.domain.entity;

import com.co.kr.modyeo.api.bbs.domain.entity.link.ArticleRecommend;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "ARTICLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "is_hidden")
    @Enumerated(value = EnumType.STRING)
    private Yn isHidden;

    @Column(name = "hit_count")
    private Long hitCount;

    @Column(name = "reply_count")
    private int replyCount;

    @Column(name = "recommend_count")
    private int recommendCount;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<ArticleRecommend> articleRecommendList = new ArrayList<>();

    @Builder(builderClassName = "of", builderMethodName = "of")
    public Article(Long id, Category category, String title, String content, String filePath, Yn isHidden, Long hitCount, int replyCount, int recommendCount, List<Reply> replyList, List<ArticleRecommend> articleRecommendList) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.hitCount = hitCount;
        this.replyCount = replyCount;
        this.recommendCount = recommendCount;
        this.replyList = replyList;
        this.articleRecommendList = articleRecommendList;
    }

    @Builder(builderMethodName = "createArticleBuilder", builderClassName = "createArticleBuilder")
    public Article(Category category, String title, String content, String filePath, Yn isHidden) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.replyCount = 0;
        this.recommendCount = 0;
        this.hitCount = 0L;
    }

    public void plusHitCount() {
        this.hitCount++;
    }

    @Builder(builderMethodName = "updateArticleBuilder", builderClassName = "updateArticleBuilder")
    public void changeArticle(Category category, String title, String content, String filePath, Yn isHidden) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
    }

    public void plusReplyCount() {
        this.replyCount++;
    }

    public void minusReplyCount() {
        this.replyCount--;
    }

    public void plusRecommendCount() {
        this.recommendCount++;
    }

    public void minusRecommendCount() {
        this.recommendCount--;
    }
}
