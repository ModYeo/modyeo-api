package com.co.kr.modyeo.api.notice.domain.entity;

import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "NOTICE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "notice_title")
    private String title;

    @Lob
    private String content;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "use_yn")
    private Yn useYn;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public Notice(Long id, String title, String content, String imagePath, Yn useYn) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.useYn = useYn;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static Notice create(String title, String content, String imagePath){
        return of()
                .title(title)
                .content(content)
                .imagePath(imagePath)
                .useYn(Yn.Y)
                .build();
    }

    @Builder(builderMethodName = "updateBuilder", builderClassName = "updateBuilder")
    public static void change(Notice notice, String title, String content, String imagePath, Yn useYn){
        notice.title = title;
        notice.content = content;
        notice.imagePath = imagePath;
        notice.useYn = useYn;
    }
}
