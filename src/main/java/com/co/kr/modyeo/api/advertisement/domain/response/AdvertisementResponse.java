package com.co.kr.modyeo.api.advertisement.domain.response;

import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvertisementResponse {

    private Long advertisementId;

    private String advertisementName;

    private String urlLink;

    private String imagePath;

    private AdvertisementType type;

    private Yn useYn;

    @Builder(builderMethodName = "of",builderClassName = "of")
    public AdvertisementResponse(Long id, String name, String urlLink, String imagePath, AdvertisementType type, Yn useYn) {
        this.advertisementId = id;
        this.advertisementName = name;
        this.urlLink = urlLink;
        this.imagePath = imagePath;
        this.type = type;
        this.useYn = useYn;
    }

    public static AdvertisementResponse toDto(Advertisement advertisement){
        return of()
                .id(advertisement.getId())
                .name(advertisement.getName())
                .imagePath(advertisement.getImagePath())
                .urlLink(advertisement.getUrlLink())
                .build();
    }
}
