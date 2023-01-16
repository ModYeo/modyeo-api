package com.co.kr.modyeo.api.notice.domain.dto.request;

import com.co.kr.modyeo.api.notice.domain.entity.Notice;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeCreateRequest {

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
