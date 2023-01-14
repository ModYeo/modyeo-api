package com.co.kr.modyeo.api.notice.domain.dto.response;

import com.co.kr.modyeo.api.notice.domain.entity.Notice;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeDetail {

    private Long id;

    private String title;

    private String content;

    private Yn useYn;

    private String imagePath;

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public NoticeDetail(Long id, String title, String content, Yn useYn, String imagePath, Long createdBy, Long updatedBy, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.useYn = useYn;
        this.imagePath = imagePath;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public static NoticeDetail toDto(Notice notice){
        return of()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .imagePath(notice.getImagePath())
                .useYn(notice.getUseYn())
                .createdBy(notice.getCreatedBy())
                .createdTime(notice.getCreatedDate())
                .updatedBy(notice.getUpdatedBy())
                .updatedTime(notice.getLastModifiedDate())
                .build();
    }
}
