package com.co.kr.modyeo.api.notice.domain.dto.response;

import com.co.kr.modyeo.api.notice.domain.entity.Notice;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeResponse {

    private Long id;

    private String title;

    private String content;

    private String imagePath;

    @Builder(builderMethodName = "of",builderClassName = "of")
    public NoticeResponse(Long id, String title, String content, String imagePath) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
    }

    public static NoticeResponse toDto(Notice notice) {
        return of()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .imagePath(notice.getImagePath())
                .build();
    }
}
