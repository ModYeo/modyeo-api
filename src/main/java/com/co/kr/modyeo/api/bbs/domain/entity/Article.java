package com.co.kr.modyeo.api.bbs.domain.entity;

import com.co.kr.modyeo.common.entity.BaseEntity;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;

    private String content;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @Column(name = "hit_count")
    private Long hitCount;

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Article(Long id, String content, String filePath, Boolean isHidden, Long hitCount, List<Reply> replyList) {
        this.id = id;
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.hitCount = hitCount;
        this.replyList = replyList;
    }

    @Builder(builderMethodName = "createArticleBuilder",builderClassName = "createArticleBuilder")
    public Article(String content, String filePath, Boolean isHidden) {
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.hitCount = 0L;
    }

    @Builder(builderMethodName = "updateArticleBuilder",builderClassName = "updateArticleBuilder")
    public Article(String content, String filePath, Boolean isHidden,Long hitCount){
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.hitCount = hitCount;
    }

    public void plusHitCount(){
        this.hitCount++;
    }
}
