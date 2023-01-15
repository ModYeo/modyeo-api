package com.co.kr.modyeo.api.notice.domain.dto.request;

import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class NoticeUpdateRequest {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    private String content;

    private String imagePath;

    @NotNull
    private Yn useYn;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public NoticeUpdateRequest(Long id, String title, String content, String imagePath, Yn useYn) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.useYn = useYn;
    }
}
