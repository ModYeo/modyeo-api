package com.co.kr.modyeo.api.notice.domain.dto.request;

import com.co.kr.modyeo.api.notice.domain.entity.Notice;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class NoticeCreateRequest {

    @NotNull
    private String title;

    private String content;

    private String imagePath;

    public static Notice toEntity(NoticeCreateRequest noticeCreateRequest){
        return Notice.createBuilder()
                .title(noticeCreateRequest.getTitle())
                .content(noticeCreateRequest.getContent())
                .imagePath(noticeCreateRequest.getImagePath())
                .build();
    }
}
